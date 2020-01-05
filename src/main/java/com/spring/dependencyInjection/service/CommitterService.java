package com.spring.dependencyInjection.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.dependencyInjection.entity.Committer;
import com.spring.dependencyInjection.exception.MyException;
import com.spring.dependencyInjection.repository.CommitterRepository;

@Service
public class CommitterService {

	private static final Logger logger = LoggerFactory.getLogger(CommitterService.class);

	@Autowired
	private CommitterRepository committerRespository;

	public Committer createCommitterResource(Committer committer) {
		Committer committerToDB = null;
		try {
			committerToDB = committerRespository.save(committer);
			if (committerToDB == null) {
				throw new MyException("Sorry Could Not Persist Committer Resource To DB");
			}
		} catch (final MyException ex) {
			logger.error(ex.getMessage());
		}
		return committerToDB;
	}
}





/*


  "committer": {
    "login": "web-flow",
    "id": 19864447,
    "node_id": "MDQ6VXNlcjE5ODY0NDQ3",
    "avatar_url": "https://avatars3.githubusercontent.com/u/19864447?v=4",
    "gravatar_id": "",
    "url": "https://api.github.com/users/web-flow",
    "html_url": "https://github.com/web-flow",
    "followers_url": "https://api.github.com/users/web-flow/followers",
    "following_url": "https://api.github.com/users/web-flow/following{/other_user}",
    "gists_url": "https://api.github.com/users/web-flow/gists{/gist_id}",
    "starred_url": "https://api.github.com/users/web-flow/starred{/owner}{/repo}",
    "subscriptions_url": "https://api.github.com/users/web-flow/subscriptions",
    "organizations_url": "https://api.github.com/users/web-flow/orgs",
    "repos_url": "https://api.github.com/users/web-flow/repos",
    "events_url": "https://api.github.com/users/web-flow/events{/privacy}",
    "received_events_url": "https://api.github.com/users/web-flow/received_events",
    "type": "User",
    "site_admin": false
  }
  
  */
