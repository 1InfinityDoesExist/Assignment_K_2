package com.spring.dependencyInjection.controller;

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
import com.spring.dependencyInjection.entity.Commits;
import com.spring.dependencyInjection.entity.Committer;
import com.spring.dependencyInjection.exception.MyException;
import com.spring.dependencyInjection.service.AuthorService;
import com.spring.dependencyInjection.service.CommitService;
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

		try {
			HttpResponse<JsonNode> jsonNode = Unirest.get(url).header("Content-Type", "application/json")
					.header("Accept", "application/json").asJson();
			JSONArray jsonArray = jsonNode.getBody().getArray();
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

					default:
						logger.info("************************End of Switch Case Along******************");

					}

				}

			}

			Author authorToDB = authorService.saveResourceInDB(authorMain);

			if (authorToDB == null) {
				throw new MyException("Sorry Could Not Persist author in DB");
			}
			//throw new RuntimeException();
		} catch (UnirestException | MyException e) {
			// TODO Auto-generated catch block
			logger.error("****************Inside Exception ***************************");
			e.printStackTrace();
		}
		return null;

	}

}
