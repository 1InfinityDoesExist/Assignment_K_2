package com.spring.dependencyInjection.service;

import java.lang.reflect.InvocationTargetException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.spring.dependencyInjection.entity.Author;
import com.spring.dependencyInjection.entity.CommitAuthorDetails;
import com.spring.dependencyInjection.entity.CommitCommitter;
import com.spring.dependencyInjection.entity.CommitTree;
import com.spring.dependencyInjection.entity.CommitVerification;
import com.spring.dependencyInjection.entity.Commits;
import com.spring.dependencyInjection.entity.Committer;
import com.spring.dependencyInjection.exception.MyException;
import com.spring.dependencyInjection.repository.CommitRepository;
import com.spring.dependencyInjection.util.ReflectionUtil;

@Service
public class CommitService {

	private static final Logger logger = LoggerFactory.getLogger(CommitService.class);

	ReflectionUtil refUtil = ReflectionUtil.getInstance();

	@Autowired
	ObjectMapper objectMapper;

	@PostConstruct
	public void setUp() {

		objectMapper.registerModule(new JavaTimeModule());
	}

	@Autowired
	private CommitRepository commitRepository;

	public Commits saveCommitsResource(Commits commits) {
		Commits commitToDB = null;
		try {
			commitToDB = commitRepository.save(commits);
			if (commitToDB == null) {
				throw new MyException("Sorry Could Not Persist Commit Resource In The DB");
			}
		} catch (final MyException ex) {
			logger.error(ex.getMessage());
		}
		return commitToDB;
	}

	public Commits retrieveCommitsResourceById(Long id) {
		Commits commitFromDB = null;
		try {
			commitFromDB = commitRepository.getAuthorResourceByID(id);
			if (commitFromDB == null) {
				throw new MyException("Sorry Could Not Retrieve Author Resource From DB");
			}
		} catch (final MyException ex) {
			logger.error(ex.getMessage());
		}
		return commitFromDB;
	}

	public List<Commits> retrieveAllCommits() {
		List<Commits> listOfCommits = null;
		try {
			listOfCommits = commitRepository.getAllAuthorResource();
			if (listOfCommits == null || listOfCommits.size() == 0) {
				throw new MyException("Sorry Could Not Retrieve Data From DB");
			}
		} catch (final MyException ex) {
			logger.error(ex.getMessage());
		}
		return listOfCommits;

	}

	public String deleteCommitsByID(Long id) {
		Commits commitsFromDB = retrieveCommitsResourceById(id);
		String response = null;
		if (commitsFromDB == null) {
			return response;
		}
		try {
			commitRepository.deleteCommitsById(id);
			return "SuccessFully Deleted";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error(e.getMessage());
		}
		return response;
	}

	// Partial Update
	public Commits updateByID(String commit, Long id) throws JsonMappingException, JsonProcessingException,
			ParseException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, JSONException {
		// TODO Auto-generated method stub
		Commits commitFromDB = retrieveCommitsResourceById(id);
		if (commitFromDB == null) {
			return null;
		}
		// Could Not Parse LocalDateTime So need to Do This...!!!
		Commits commitsFromPayload = objectMapper.readValue(commit, Commits.class);
		LocalDateTime commitCommitDate = commitsFromPayload.getCommit().getAuthor().getDate();
		LocalDateTime commitCommitterDate = commitsFromPayload.getCommit().getCommitter().getDate();

		JSONParser jsonParser = new JSONParser();
		JSONObject jsonObject = (JSONObject) jsonParser.parse(commit);
		for (Iterator iterator = ((Map<String, String>) jsonObject).keySet().iterator(); iterator.hasNext();) {

			String propName = (String) iterator.next();
			if (propName.equals("author")) {
				logger.info("******************Inside Commit Author*****************************");
				if (jsonObject.get(propName) != null) {
					JSONObject authorJsonObject = (JSONObject) jsonObject.get(propName);
					for (Object authorObject : authorJsonObject.keySet()) {
						String authorPropName = (String) authorObject;
						if (commitFromDB.getAuthor_id() == null) {
							commitFromDB.setAuthor_id(new Author());
						}
						refUtil.getSetterMethod("Author", authorPropName).invoke(commitFromDB.getAuthor_id(),
								authorJsonObject.get(authorPropName));
					}
				} else {
					commitFromDB.setAuthor_id(null);
				}

			} else if (propName.equals("committer")) {
				logger.info("****************************Inside Commit Committer********************");
				if (jsonObject.get(propName) != null) {
					JSONObject committerObject = (JSONObject) jsonObject.get(propName);
					for (Object committerObj : committerObject.keySet()) {
						String committerPropName = (String) committerObj;
						if (commitFromDB.getCommitter_id() == null) {
							commitFromDB.setCommitter_id(new Committer());
						}
						refUtil.getSetterMethod("Committer", committerPropName).invoke(commitFromDB.getCommitter_id(),
								committerObject.get(committerPropName));
					}
				} else {
					commitFromDB.setCommitter_id(null);
				}

			} else if (propName.equals("commit")) {
				logger.info("******************* Inside Commits Commit ************************");
				if (jsonObject.get(propName) != null) {
					JSONObject commitObject = (JSONObject) jsonObject.get(propName);
					for (Object commitObj : commitObject.keySet()) {
						String commitObjPropName = (String) commitObj;
						if (commitObjPropName.equals("author")) {
							logger.info("**********************Inside Commit Author*****************");
							if (commitObject.get(commitObjPropName) != null) {

								JSONObject commitAuthorObject = (JSONObject) commitObject.get(commitObjPropName);
								for (Object commitAuthorObj : commitAuthorObject.keySet()) {
									String commitAuthorObjPropName = (String) commitAuthorObj;

									if (commitFromDB.getCommit().getAuthor() == null) {
										commitFromDB.getCommit().setAuthor(new CommitAuthorDetails());
									}
									if (commitAuthorObjPropName.equals("date")) {
										logger.info("****************Parsing DateTime *************************");
										DateTimeFormatter dateFormatter = DateTimeFormatter
												.ofPattern("yyyy-MM-ss'T'HH:mm:ss.SSS'Z'");
										LocalDateTime dateTime = LocalDateTime.parse(commitAuthorObjPropName,
												dateFormatter);
										commitFromDB.getCommit().getAuthor().setDate(dateTime);
									} else {
										refUtil.getSetterMethod("CommitAuthorDetails", commitAuthorObjPropName).invoke(
												commitFromDB.getCommit().getAuthor(),
												commitAuthorObject.get(commitAuthorObjPropName));
									}

								}
							} else {
								commitFromDB.getCommit().setAuthor(null);
							}

						} else if (commitObjPropName.equals("committer")) {
							logger.info("***************************** Inside Commit Committer *********************");
							if (commitObject.get(commitObjPropName) != null) {
								JSONObject commitCommitterObject = (JSONObject) commitObject.get(commitObjPropName);
								for (Object committerObj : commitCommitterObject.keySet()) {
									String committerPropName = (String) committerObj;
									if (commitFromDB.getCommit().getCommitter() == null) {
										commitFromDB.getCommit().setCommitter(new CommitCommitter());
									}

									if (committerPropName.equals("date")) {
										logger.info(
												"********************Inside Date Committer of Commit*****************************");
										DateTimeFormatter formatter = DateTimeFormatter
												.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
										LocalDateTime committerDate = LocalDateTime.parse(committerPropName, formatter);
										commitFromDB.getCommit().getCommitter().setDate(committerDate);
									} else {
										refUtil.getSetterMethod("CommitCommitter", committerPropName).invoke(
												commitFromDB.getCommit().getCommitter(),
												commitCommitterObject.get(committerPropName));
									}
								}
							} else {
								commitFromDB.getCommit().setCommitter(null);
							}
						} else if (commitObjPropName.equals("tree")) {
							logger.info("************************Commit Tree *********************************");
							if (commitObject.get(commitObjPropName) != null) {
								JSONObject jsonObjectTree = (JSONObject) commitObject.get(commitObjPropName);
								for (Object treeObject : jsonObjectTree.keySet()) {
									String treePropName = (String) treeObject;
									if (commitFromDB.getCommit().getTree() == null) {
										commitFromDB.getCommit().setTree(new CommitTree());
									}
									refUtil.getSetterMethod("CommitTree", treePropName).invoke(
											commitFromDB.getCommit().getTree(), jsonObjectTree.get(treePropName));
								}
							} else {
								commitFromDB.getCommit().setTree(null);
							}

						} else if (commitObjPropName.equals("verification")) {
							logger.info("***********************Commit Verification**********************");
							if (commitObject.get(commitObjPropName) != null) {
								JSONObject verificationObject = (JSONObject) commitObject.get(commitObjPropName);
								for (Object verObject : verificationObject.keySet()) {
									String verificationPropName = (String) verObject;
									if (commitFromDB.getCommit().getVerification() == null) {
										commitFromDB.getCommit().setVerification(new CommitVerification());
									}
									refUtil.getSetterMethod("CommitVerification", verificationPropName).invoke(
											commitFromDB.getCommit().getVerification(),
											verificationObject.get(verificationPropName));
								}
							} else {
								commitFromDB.getCommit().setVerification(null);
							}
						} else {
							logger.info("*****************Commits Commit Normal Update*********************");
							refUtil.getSetterMethod("Commit", commitObjPropName).invoke(commitFromDB.getCommit(),
									commitObject.get(commitObjPropName));
						}
					}
				} else {
					commitFromDB.setCommit(null);
				}
				// List of parents Partial Update...!!!
			} else if (propName.equals("parents")) {
				logger.info("*******************Parents Case **************************");
				JSONArray jsonArray = (JSONArray) jsonObject.get(propName);
				for (int iter = 0; iter < jsonArray.length(); iter++) {
					JSONObject jsonParentObject = (JSONObject) jsonArray.get(iter);
					for (Object jsonParentObj : jsonParentObject.keySet()) {
						String jsonParentPropName = (String) jsonParentObj;
						// Reflection Utility
						refUtil.getSetterMethod("Parents", jsonParentPropName).invoke(commitFromDB.getParents(),
								jsonParentObject.get(jsonParentPropName));
					}
				}
			} else {
				logger.info("***********************Indside Simple Object ****************************");
				refUtil.getSetterMethod("Commits", propName).invoke(commitFromDB, jsonObject.get(propName));
			}
		}
		Commits commitsToDB = commitRepository.save(commitFromDB);
		return commitsToDB;
	}
}
