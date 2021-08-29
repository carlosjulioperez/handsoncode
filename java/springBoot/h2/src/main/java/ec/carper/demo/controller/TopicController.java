package ec.carper.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import ec.carper.demo.model.Topic;
import ec.carper.demo.service.TopicService;

@RestController
@RequestMapping(value="/topic")
public class TopicController {
    
    @Autowired
    TopicService topicService;
	
    @RequestMapping(value = "/update", method = RequestMethod.PUT)
	@ResponseBody
	public Topic updateTopic(@RequestBody Topic topic) {
		Topic topicResponse = (Topic) topicService.updateTopic(topic);
		return topicResponse;
	}
	

}
