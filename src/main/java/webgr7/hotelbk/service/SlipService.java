package webgr7.hotelbk.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import webgr7.hotelbk.repository.SlipRepository;

@Service
public class SlipService {
    @Autowired
    SlipRepository slipRepository;


}
