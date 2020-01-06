package com.spring.dependencyInjection.controller;

import java.time.LocalDateTime;

import javax.transaction.Transactional;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
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

@RestController
@RequestMapping(path = "/api/object/commit")
public class CommitController {

	private static final Logger logger = LoggerFactory.getLogger(CommitController.class);

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

		final String url = "https://api.github.com/repositories/19438/commits";
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
			HttpResponse<JsonNode> jsonNode = Unirest.get(url).header("Content-Type", "application/json")
					.header("Accept", "application/json").asJson();
			JSONArray jsonArray = jsonNode.getBody().getArray();
			logger.info("ArrayLenght is :-  " + jsonArray.length());
			for (int iter = 0; iter < jsonArray.length(); iter++) {
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

					case "sha":
						Object shaObject = jsonObject.get(propName);
						String shaValue = (String) shaObject;
						commitsMain.setSha(shaValue);
						continue;

					case "node_id":
						Object nodeObject = jsonObject.get(propName);
						String nodeValue = (String) nodeObject;
						commitsMain.setNodeId(nodeValue);
						continue;

					case "commit":
						commitMain = new Commit();
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
										LocalDateTime dateValue = (LocalDateTime) dateObject;
										commitAuthorMain.setDate(dateValue);
										continue;

									default:
										logger.info("****End of Commit Author Switch Case********");
									}
								}
								// CommitAuthor
								commitMain.setAuthor(commitAuthorMain);

							case "committer":
								commitCommitterMain = new CommitCommitter();
								JSONObject cCommitterObject = (JSONObject) cObject.get(cPropName);
								for (Object ccObj : cCommitterObject.keySet()) {
									String ccPropName = (String) ccObj;
									switch (ccPropName) {

									case "name":
										Object nameObject = cCommitterObject.get(ccPropName);
										String nameValue = (String) nameObject;
										commitCommitterMain.setName(nameValue);
										continue;

									case "email":
										Object emailObject = cCommitterObject.get(ccPropName);
										String emailValue = (String) emailObject;
										commitCommitterMain.setEmail(emailValue);
										continue;

									case "date":
										Object dateObject = cCommitterObject.get(ccPropName);
										LocalDateTime dateValue = (LocalDateTime) dateObject;
										commitCommitterMain.setDate(dateValue);
										continue;

									default:
										logger.info(
												"*********************Commit Committer Switch Case*****************");
									}
								}
								commitMain.setCommitter(commitCommitterMain);

							case "message":
								Object messageObject = cObject.get(cPropName);
								String messageValue = (String) messageObject;
								commitMain.setMessage(messageValue);
								continue;

							case "tree":

							case "url":
								Object urlObject = cObject.get(cPropName);
								String urlString = (String) urlObject;
								commitMain.setUrl(urlString);
								continue;
							case "comment_count":
							case "verification":
							default:
								logger.info("*******End of Commit object Case******");
							}
						}

					case "url":
						Object urlObject = jsonObject.get(propName);
						String urlValue = (String) urlObject;
						commitsMain.setUrl(urlValue);
						continue;

					case "html_url":
						Object htmlObject = jsonObject.get(propName);
						String htmlValue = (String) htmlObject;
						commitsMain.setHtmlUrl(htmlValue);
						continue;

					case "comments_url":
						Object commentsObject = jsonObject.get(propName);
						String commentValue = (String) commentsObject;
						commitsMain.setCommentsUrl(commentValue);
						continue;

					case "parents":
						parents = new Parents();

					default:
						logger.info("************************End of Switch Case Along******************");

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

			}

			// throw new RuntimeException();
		} catch (UnirestException | MyException e) {
			// TODO Auto-generated catch block
			logger.error("****************Inside Exception ***************************");
			e.printStackTrace();
		}
		return null;

	}

}
