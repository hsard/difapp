package com.waes.diffapp.service;

import com.waes.diffapp.constants.DiffSide;
import com.waes.diffapp.model.DiffResult;

/**
 * Diff Service interface
 *
 * @author HS
 */
public interface DiffService {
    /**
     * Saves requested content(id,side and content) into object store
     *
     * @param sessionId Diff session id
     * @param diffSide  Side parameter the content should be uploaded to
     * @param content   Actual content to be uploaded
     */
    void uploadContent(String sessionId, DiffSide diffSide, String content);

    /**
     * Analyzes and finds differences for the complete diff entity
     *
     * @param sessionId the transaction id for the operation
     * @return the contents of a difference request.
     * //     * @throws TransactionIncompleteException if not both panels are loaded.
     */
    DiffResult analyze(String sessionId);
}
