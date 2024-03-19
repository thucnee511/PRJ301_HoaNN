package org.hoann.prj301.services;

import java.util.List;
import org.hoann.prj301.dao.FeedbackDAO;
import org.hoann.prj301.repositories.feedback.FeedbackDTO;

public class FeedbackService {

    private static FeedbackService instance;
    private static final FeedbackDAO dao = FeedbackDAO.getInstance();

    private FeedbackService() {
    }

    public static FeedbackService getInstance() {
        return instance = instance == null ? new FeedbackService() : instance;
    }

    public List<FeedbackDTO> get() {
        return dao.get();
    }
}
