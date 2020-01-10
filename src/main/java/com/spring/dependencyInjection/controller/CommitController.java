package com.spring.dependencyInjection.controller;

import java.lang.reflect.InvocationTargetException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.spring.dependencyInjection.constant.Constants;
import com.spring.dependencyInjection.entity.Author;
import com.spring.dependencyInjection.entity.Commit;
import com.spring.dependencyInjection.entity.CommitAuthorDetails;
import com.spring.dependencyInjection.entity.CommitCommitter;
import com.spring.dependencyInjection.entity.CommitTree;
import com.spring.dependencyInjection.entity.CommitVerification;
import com.spring.dependencyInjection.entity.Commits;
import com.spring.dependencyInjection.entity.Committer;
import com.spring.dependencyInjection.entity.Parents;
import com.spring.dependencyInjection.exception.MyException;
import com.spring.dependencyInjection.service.AuthorService;
import com.spring.dependencyInjection.service.CommitService;
import com.spring.dependencyInjection.service.CommitterService;
import com.spring.dependencyInjection.service.ErrorMapping;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping(path = "/api/object/commit")
public class CommitController {

	// private static final Logger logger =
	// LoggerFactory.getLogger(CommitController.class);
	private static final Logger logger = Logger.getLogger(CommitController.class);

	@Autowired
	private ErrorMapping errorMapping;

	@Autowired
	private AuthorService authorService;

	@Autowired
	private CommitterService committerService;

	@Autowired
	private CommitService commitService;

	@RequestMapping(path = "/automatic", method = RequestMethod.GET)
	@ApiOperation(value = "/automatic", notes = "Automaticlly Data Will Be Persisted In Respective Table In The Database")
	@Transactional
	public ResponseEntity<?> createResource() {
		logger.info("******************Inside CommitController Method************************");

		List<Commits> listOfCommits = new ArrayList<Commits>();

		Committer committerMain = null;
		Author authorMain = null;
		Commits commitsMain = null;
		Parents parents = null;
		Commit commitMain = null;
		CommitAuthorDetails commitAuthorMain = null;
		CommitCommitter commitCommitterMain = null;
		CommitTree commitTree = null;
		CommitVerification commitVerification = null;

		try {
			HttpResponse<JsonNode> jsonNode = Unirest.get(Constants.url).header("Content-Type", "application/json")
					.header("Accept", "application/json").asJson();
			JSONArray jsonArray = jsonNode.getBody().getArray();
			logger.info("ArrayLenght is :-  " + jsonArray.length());
			for (int iter = 0; iter < jsonArray.length(); iter++) {
				commitsMain = new Commits();// Instantiating the Commits Class...!!!
				JSONObject jsonObject = (JSONObject) jsonArray.get(iter);
				for (Object object : jsonObject.keySet()) {
					String propName = (String) object;

					switch (propName) {
					// Setting Author Value
					case "author":
						authorMain = new Author();
						JSONObject authorObject = (JSONObject) jsonObject.get(propName);
						for (Object authObject : authorObject.keySet()) {
							String authPropName = (String) authObject;
							switch (authPropName) {
							case "login":
								Object loginObject = authorObject.get(authPropName);
								String loginValue = (String) loginObject;
								authorMain.setLogin(loginValue);
								continue;
							case "node_id":
								Object nodeIdObject = authorObject.get(authPropName);
								String nodeIdValue = (String) nodeIdObject;
								authorMain.setNodeId(nodeIdValue);
								continue;

							case "avatar_url":
								Object avatarObject = authorObject.get(authPropName);
								String avatarValue = (String) avatarObject;
								authorMain.setAvatarUrl(avatarValue);
								continue;

							case "gravatar_id":
								Object gravatorObject = authorObject.get(authPropName);
								String gravatorValue = (String) gravatorObject;
								authorMain.setGravatarId(gravatorValue);
								continue;
							case "url":
								Object urlObject = authorObject.get(authPropName);
								String urlValue = (String) urlObject;
								authorMain.setUrl(urlValue);
								continue;

							case "html_url":
								Object htmlObject = authorObject.get(authPropName);
								String htmlValue = (String) htmlObject;
								authorMain.setHtmlUrl(htmlValue);
								continue;

							case "followers_url":
								Object followersObject = authorObject.get(authPropName);
								String followersValue = (String) followersObject;
								authorMain.setFollowersUrl(followersValue);
								continue;

							case "following_url":
								Object followingObject = authorObject.get(authPropName);
								String followingValue = (String) followingObject;
								authorMain.setFollowingUrl(followingValue);
								continue;

							case "gists_url":
								Object gistsObject = authorObject.get(authPropName);
								String gistsValue = (String) gistsObject;
								authorMain.setGistsUrl(gistsValue);
								continue;

							case "starred_url":
								Object starredObject = authorObject.get(authPropName);
								String starredValue = (String) starredObject;
								authorMain.setStarredUrl(starredValue);
								continue;

							case "subscriptions_url":
								Object subscriptionObject = authorObject.get(authPropName);
								String subscriptionValue = (String) subscriptionObject;
								authorMain.setSubscriptionsUrl(subscriptionValue);
								continue;

							case "organizations_url":
								Object organizationUrlObject = authorObject.get(authPropName);
								String orgValue = (String) organizationUrlObject;
								authorMain.setOrganizationsUrl(orgValue);
								continue;

							case "repos_url":
								Object reposObject = authorObject.get(authPropName);
								String repoValue = (String) reposObject;
								authorMain.setReposUrl(repoValue);
								continue;

							case "events_url":
								Object eventObject = authorObject.get(authPropName);
								String eventValue = (String) eventObject;
								authorMain.setEventsUrl(eventValue);
								continue;

							case "received_events_url":
								Object receivedObject = authorObject.get(authPropName);
								String receivedValue = (String) receivedObject;
								authorMain.setReceivedEventsUrl(receivedValue);
								continue;

							case "type":
								Object typeObject = authorObject.get(authPropName);
								String typeValue = (String) typeObject;
								authorMain.setType(typeValue);
								continue;

							case "site_admin":
								Object siteObject = authorObject.get(authPropName);
								Boolean siteValue = (Boolean) siteObject;
								authorMain.setSiteAdmin(siteValue);
								continue;

							default:
								logger.info("**********End Of Auther Switch Case****************");
							}

						}
						continue;

					case "committer":
						committerMain = new Committer();
						JSONObject committerObject = (JSONObject) jsonObject.get(propName);
						for (Object commitObject : committerObject.keySet()) {
							String commitPropName = (String) commitObject;
							switch (commitPropName) {
							case "login":
								Object loginObject = committerObject.get(commitPropName);
								String loginValue = (String) loginObject;
								committerMain.setLogin(loginValue);
								continue;

							case "node_id":
								Object nodeObject = committerObject.get(commitPropName);
								String nodeValue = (String) nodeObject;
								committerMain.setNodeId(nodeValue);
								continue;

							case "avatar_url":
								Object avatarObject = committerObject.get(commitPropName);
								String avatarValue = (String) avatarObject;
								committerMain.setAvatorUrl(avatarValue);
								continue;

							case "gravatar_id":
								Object gravatar = committerObject.get(commitPropName);
								String gravatarValue = (String) gravatar;
								committerMain.setGravatarId(gravatarValue);
								continue;

							case "url":
								Object urlObject = committerObject.get(commitPropName);
								String urlValue = (String) urlObject;
								committerMain.setUrl(urlValue);
								continue;

							case "html_url":
								Object htmlObject = committerObject.get(commitPropName);
								String htmlValue = (String) htmlObject;
								committerMain.setHtmlUrl(htmlValue);
								continue;

							case "followers_url":
								Object followersObject = committerObject.get(commitPropName);
								String followersValue = (String) followersObject;
								committerMain.setFollowersUrl(followersValue);
								continue;

							case "following_url":
								Object followingObject = committerObject.get(commitPropName);
								String followingValue = (String) followingObject;
								committerMain.setFollowingUrl(followingValue);
								continue;

							case "gists_url":
								Object gistObject = committerObject.get(commitPropName);
								String gistValue = (String) gistObject;
								committerMain.setGistsUrl(gistValue);
								continue;

							case "starred_url":
								Object starredObject = committerObject.get(commitPropName);
								String starredValue = (String) starredObject;
								committerMain.setStarredUrl(starredValue);
								continue;

							case "subscriptions_url":
								Object subscriptionsObject = committerObject.get(commitPropName);
								String subscriptionValue = (String) subscriptionsObject;
								committerMain.setSubscriptionsUrl(subscriptionValue);
								continue;

							case "organizations_url":
								Object organizationsObject = committerObject.get(commitPropName);
								String organizationValue = (String) organizationsObject;
								committerMain.setOrganizationsUrl(organizationValue);
								continue;

							case "repos_url":
								Object repoObject = committerObject.get(commitPropName);
								String repoValue = (String) repoObject;
								committerMain.setReposUrl(repoValue);
								continue;

							case "events_url":
								Object eventObject = committerObject.get(commitPropName);
								String eventValue = (String) eventObject;
								committerMain.setEventsUrl(eventValue);
								continue;

							case "received_events_url":
								Object receivedObject = committerObject.get(commitPropName);
								String receivedValue = (String) receivedObject;
								committerMain.setReceivedEventsUrl(receivedValue);
								continue;

							case "type":
								Object typeObject = committerObject.get(commitPropName);
								String typeValue = (String) typeObject;
								committerMain.setType(typeValue);
								continue;

							case "site_admin":
								Object siteObject = committerObject.get(commitPropName);
								Boolean siteValue = (Boolean) siteObject;
								committerMain.setSiteAdmin(siteValue);
								continue;

							default:
								logger.info("***************End Of Commiter Statement*****************");
							}
						}
						continue;

					case "sha":
						logger.info("***********Setting the sha value*************");
						Object shaObject = jsonObject.get(propName);
						String shaValue = (String) shaObject;
						commitsMain.setSha(shaValue);
						continue;

					case "node_id":
						logger.info("*************Setting nodeId************");
						Object nodeObject = jsonObject.get(propName);
						String nodeValue = (String) nodeObject;
						commitsMain.setNodeId(nodeValue);
						continue;

					case "commit":
						logger.info("*****************Commit Switch Case**************");
						commitMain = new Commit();// Instantiating Commit Class
						JSONObject cObject = (JSONObject) jsonObject.get(propName);
						for (Object commitObj : cObject.keySet()) {
							String cPropName = (String) commitObj;
							switch (cPropName) {
							case "author":
								commitAuthorMain = new CommitAuthorDetails();
								JSONObject cAuthorObject = (JSONObject) cObject.get(cPropName);
								for (Object cAuthObj : cAuthorObject.keySet()) {
									String cAuthPropName = (String) cAuthObj;
									switch (cAuthPropName) {
									case "name":
										Object nameObject = cAuthorObject.get(cAuthPropName);
										String nameValue = (String) nameObject;
										commitAuthorMain.setName(nameValue);
										continue;

									case "email":
										Object emailObject = cAuthorObject.get(cAuthPropName);
										String emailValue = (String) emailObject;
										commitAuthorMain.setEmail(emailValue);
										continue;

									case "date":
										Object dateObject = cAuthorObject.get(cAuthPropName);
										String dateValue = (String) dateObject;
										DateTimeFormatter formatter = DateTimeFormatter
												.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'");
										LocalDateTime date = LocalDateTime.parse(dateValue, formatter);
										commitAuthorMain.setDate(date);
										continue;

									default:
										logger.info("****End of Commit Author Switch Case********");
									}
								}
								// CommitAuthor
								commitMain.setAuthor(commitAuthorMain);
								continue;

							case "committer":
								logger.info("*************Inside commit commiter***************");
								commitCommitterMain = new CommitCommitter();
								JSONObject cCommitterObject = (JSONObject) cObject.get(cPropName);
								for (Object ccObj : cCommitterObject.keySet()) {
									String ccPropName = (String) ccObj;
									switch (ccPropName) {

									case "name":
										Object nameObject = cCommitterObject.get(ccPropName);
										logger.info("Commit Committer Name:- " + nameObject);
										if (nameObject.equals(null)) {
											commitCommitterMain.setName(null);
											continue;

										}
										String nameValue = (String) nameObject;
										commitCommitterMain.setName(nameValue);
										continue;

									case "email":
										Object emailObject = cCommitterObject.get(ccPropName);
										if (emailObject.equals(null)) {
											commitCommitterMain.setEmail(null);
											continue;
										}
										String emailValue = (String) emailObject;
										commitCommitterMain.setEmail(emailValue);
										continue;

									case "date":
										Object dateObject = cCommitterObject.get(ccPropName);
										String date = (String) dateObject;
										DateTimeFormatter formatter = DateTimeFormatter
												.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'");
										LocalDateTime localDateTime = LocalDateTime.parse(date, formatter);

										commitCommitterMain.setDate(localDateTime);
										continue;

									default:
										logger.info(
												"*********************Commit Committer Switch Case*****************");
									}
								}
								commitMain.setCommitter(commitCommitterMain);
								continue;

							case "message":
								logger.info("***********Inside Commit Message*****************");
								Object messageObject = cObject.get(cPropName);
								String messageValue = (String) messageObject;
								commitMain.setMessage(messageValue);
								continue;

							case "tree":
								logger.info("*************Inside Commit Tree****************");
								commitTree = new CommitTree();
								JSONObject treeObject = (JSONObject) cObject.get(cPropName);
								for (Object treeObj : treeObject.keySet()) {
									String treePropName = (String) treeObj;
									switch (treePropName) {
									case "sha":
										Object shaObj = treeObject.get(treePropName);
										String shaVal = (String) shaObj;
										commitTree.setSha(shaVal);
										continue;

									case "url":
										Object urlObj = treeObject.get(treePropName);
										String urlVal = (String) urlObj;
										commitTree.setUrl(urlVal);
										continue;

									default:
										logger.info("**************End of Tree Switch Case***************");
									}
								}

								// Tree Resource Persistence...
								commitMain.setTree(commitTree);
								continue;

							case "url":
								Object urlObject = cObject.get(cPropName);
								String urlString = (String) urlObject;
								commitMain.setUrl(urlString);
								continue;
							case "comment_count":
								Object commentCountObject = cObject.get(cPropName);
								Integer commentValue = (Integer) commentCountObject;
								commitMain.setCommentCount(commentValue);
								continue;

							case "verification":
								logger.info("**************Inside Commit Verification***********************");
								commitVerification = new CommitVerification();
								JSONObject verificationObject = (JSONObject) cObject.get(cPropName);
								for (Object veriObject : verificationObject.keySet()) {
									String veriProp = (String) veriObject;
									switch (veriProp) {
									case "verified":
										Object verifidObject = verificationObject.get(veriProp);
										if (verifidObject.equals(null)) {
											commitVerification.setVerified(null);
											continue;
										}
										Boolean verifiedValue = (Boolean) verifidObject;
										commitVerification.setVerified(verifiedValue);
										continue;

									case "reason":
										Object reasonObject = verificationObject.get(veriProp);
										if (reasonObject.equals(null)) {
											commitVerification.setReason(null);
											continue;
										}
										String reasonValue = (String) reasonObject;
										commitVerification.setReason(reasonValue);
										continue;

									case "signature":
										Object signatureObject = verificationObject.get(veriProp);
										if (signatureObject.equals(null)) {
											logger.info("Inside Signature Null Value");
											commitVerification.setSignature(null);
											continue;
										}
										String signatureValue = (String) signatureObject;
										logger.info("Commit Signature:- " + signatureValue);
										commitVerification.setSignature(signatureValue);
										continue;

									case "payload":
										Object payloadObject = verificationObject.get(veriProp);
										if (payloadObject.equals(null)) {
											commitVerification.setPayload(null);
											continue;
										}
										String payloadValue = (String) payloadObject;
										commitVerification.setPayload(payloadValue);
										continue;

									default:
										logger.info("*****************End of Verification Switch Case**************");
									}
								}

								// verificaito resource persisitence
								commitMain.setVerification(commitVerification);
								continue;

							default:
								logger.info("*******End of Commit object Case******");
							}

						}
						// Commits Commit final Instance Set
						commitsMain.setCommit(commitMain);
						continue;

					case "url":
						logger.info("********************Inside Commits Url****************");
						Object urlObject = jsonObject.get(propName);
						String urlValue = (String) urlObject;
						commitsMain.setUrl(urlValue);
						continue;

					case "html_url":
						logger.info("******************Inside Commits Html Url*******************");
						Object htmlObject = jsonObject.get(propName);
						String htmlValue = (String) htmlObject;
						commitsMain.setHtmlUrl(htmlValue);
						continue;

					case "comments_url":
						logger.info("*********************Inside Commits Comments Url***************");
						Object commentsObject = jsonObject.get(propName);
						String commentValue = (String) commentsObject;
						commitsMain.setCommentsUrl(commentValue);
						continue;

					case "parents":
						logger.info("***********************Inside Commits Parents *****************");
						parents = new Parents();
						List<Parents> listOfParents = new ArrayList<Parents>();
						JSONArray parentJsonArray = (JSONArray) jsonObject.get(propName);
						for (int jter = 0; jter < parentJsonArray.length(); jter++) {
							logger.info("*****************Inside For Loop Of Parents *****************");
							JSONObject parentJsonObject = (JSONObject) parentJsonArray.get(jter);
							for (Object parentObj : parentJsonObject.keySet()) {
								String parentValue = (String) parentObj;
								switch (parentValue) {
								case "sha":
									Object sObject = parentJsonObject.get(parentValue);
									String svalue = (String) sObject;
									parents.setSha(svalue);
									continue;

								case "url":
									Object uObject = parentJsonObject.get(parentValue);
									String uValue = (String) uObject;
									parents.setUrl(uValue);
									continue;

								case "html_url":

									Object hObject = parentJsonObject.get(parentValue);
									String hValue = (String) hObject;
									parents.setHtmlUrl(hValue);
									continue;

								default:
									logger.info("****************End of Parent Switch Case ****************");
								}
							}
							listOfParents.add(parents);
						}

						commitsMain.setParents(listOfParents);
						continue;

					default:
						logger.info("************************End of Entire Switch Case ******************");

					}

				}
				// Persisting Author Resource in the Database
				Author authorToDB = authorService.saveResourceInDB(authorMain);

				if (authorToDB == null) {
					throw new MyException("Sorry Could Not Persist author in DB");
				} else {
					commitsMain.setAuthor_id(authorToDB);
				}

				// Persisting Committer Resource in the Database
				Committer committerToDB = committerService.createCommitterResource(committerMain);
				if (committerToDB == null) {
					throw new MyException("Sorry Could Not Persiste Commiter Instance To DB");
				} else {
					commitsMain.setCommitter_id(committerToDB);
				}

				logger.info("Authord------------->" + commitsMain.getCommit().getAuthor().getEmail());
				logger.info("Authord:-------------->" + commitsMain.getCommit().getAuthor().getName());
				logger.info("Authord:-------------->" + commitsMain.getCommit().getAuthor().getDate());
				logger.info("Committer:-------------->" + commitsMain.getCommit().getCommitter().getEmail());
				logger.info("Committer:-------------->" + commitsMain.getCommit().getCommitter().getName());
				logger.info("Committer:-------------->" + commitsMain.getCommit().getCommitter().getDate());
				logger.info("Tree:-------------->" + commitsMain.getCommit().getTree().getSha());
				logger.info("Tree:-------------->" + commitsMain.getCommit().getTree().getUrl());
				logger.info("Verification:-------------->" + commitsMain.getCommit().getVerification().getPayload());
				logger.info("Verification:-------------->" + commitsMain.getCommit().getVerification().getReason());
				logger.info("Verification:-------------->" + commitsMain.getCommit().getVerification().getSignature());
				logger.info("Verification:-------------->" + commitsMain.getCommit().getVerification().getVerified());

				Commits commitsToDB = commitService.saveCommitsResource(commitsMain);

				if (commitsToDB != null) {
					listOfCommits.add(commitsToDB);
				}
			}
			logger.info("*********Number of Commits = " + listOfCommits.size());
			// throw new RuntimeException();
		} catch (UnirestException e) {
			// TODO Auto-generated catch block
			logger.error("****************Inside Unirest Exception ***************************");
			e.printStackTrace();
		} catch (final MyException ex) {
			logger.info(ex.getMessage());
		}
		return new ResponseEntity<List<Commits>>(listOfCommits, HttpStatus.CREATED);
	}

	@RequestMapping(path = "/get", method = RequestMethod.GET, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	@ApiOperation(value = "/get", notes = "Get Commits By Id", response = Commits.class)
	public ResponseEntity<?> getCommitsById(
			@ApiParam(value = "id", required = true) @RequestParam(value = "id", required = true) Long id) {
		Commits commitToDB = commitService.retrieveCommitsResourceById(id);
		if (commitToDB == null) {
			return new ResponseEntity<String>("Sorry No Id Found With Id:-" + id, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<Commits>(commitToDB, HttpStatus.OK);
	}

	@RequestMapping(path = "/get1", method = RequestMethod.GET, produces = "application/json")
	@ApiOperation(value = "/get1", notes = "Retrieve All Commits From The DB", response = Commits.class, responseContainer = "LIST")
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public ResponseEntity<?> getCommits() {
		List<Commits> listOfCommits = commitService.retrieveAllCommits();
		if (listOfCommits.size() == 0 || listOfCommits == null) {
			return new ResponseEntity<String>("Sorry No Data Found For The Commits", HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<List<Commits>>(listOfCommits, HttpStatus.BAD_REQUEST);
	}

	@RequestMapping(path = "/delete", method = RequestMethod.DELETE, produces = "text/plain")
	@ApiOperation(value = "/delete", notes = "Delete Commits Resource Form the Database", response = String.class)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public ResponseEntity<?> deleteCommitsByid(
			@ApiParam(value = "id", required = true) @RequestParam(value = "id", required = true) Long id) {
		String response = commitService.deleteCommitsByID(id);
		if (response == null) {
			return new ResponseEntity<String>("Sorry Could Not Delete The commits", HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<String>(response, HttpStatus.OK);
	}

	@RequestMapping(path = "/update", method = RequestMethod.PATCH, produces = "application/json")
	@ApiOperation(value = "/update", notes = "Partial Update", response = Commits.class)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public ResponseEntity<?> updateCommitsById(@Valid @RequestBody String commit,
			@ApiParam(value = "id", required = true) @RequestParam(value = "id", required = true) Long id)
			throws JsonMappingException, JsonProcessingException, ParseException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException, JSONException {
		Commits commitFromDB = commitService.updateByID(commit, id);
		if (commitFromDB == null) {
			return new ResponseEntity<String>("Sorry Could Not Detete Commits From DB", HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<Commits>(commitFromDB, HttpStatus.OK);
		// return null;
	}

}
