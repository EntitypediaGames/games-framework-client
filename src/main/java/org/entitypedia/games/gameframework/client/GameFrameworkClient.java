package org.entitypedia.games.gameframework.client;

import com.fasterxml.jackson.core.type.TypeReference;
import org.entitypedia.games.common.client.WordGameClient;
import org.entitypedia.games.common.model.ResultsPage;
import org.entitypedia.games.gameframework.common.api.IClueAPI;
import org.entitypedia.games.gameframework.common.api.IFeedbackAPI;
import org.entitypedia.games.gameframework.common.api.IPlayerAPI;
import org.entitypedia.games.gameframework.common.api.IWordAPI;
import org.entitypedia.games.gameframework.common.model.Clue;
import org.entitypedia.games.gameframework.common.model.Feedback;
import org.entitypedia.games.gameframework.common.model.Player;
import org.entitypedia.games.gameframework.common.model.Word;

/**
 * @author <a rel="author" href="http://autayeu.com/">Aliaksandr Autayeu</a>
 */
public class GameFrameworkClient extends WordGameClient implements IGameFrameworkClient {

    private static final TypeReference<Player> PLAYER_TYPE_REFERENCE = new TypeReference<Player>() {
    };
    private static final TypeReference<ResultsPage<Player>> PLAYERS_RP_TYPE_REFERENCE = new TypeReference<ResultsPage<Player>>() {
    };
    private static final TypeReference<Clue> CLUE_TYPE_REFERENCE = new TypeReference<Clue>() {
    };
    private static final TypeReference<Word> WORD_TYPE_REFERENCE = new TypeReference<Word>() {
    };
    private static final TypeReference<ResultsPage<Word>> WORDS_RP_TYPE_REFERENCE = new TypeReference<ResultsPage<Word>>() {
    };
    private static final TypeReference<ResultsPage<Clue>> CLUES_RP_TYPE_REFERENCE = new TypeReference<ResultsPage<Clue>>() {
    };
    private static final TypeReference<Feedback> FEEDBACK_TYPE_REFERENCE = new TypeReference<Feedback>() {
    };

    private static final String DEFAULT_API_ENDPOINT = "http://localhost:9632/gameframework/webapi/";
    private static final String SECURE_API_ENDPOINT = "https://localhost:9633/gameframework/webapi/";


    public GameFrameworkClient(String uid, String password) {
        super(DEFAULT_API_ENDPOINT, uid, password);
    }

    public GameFrameworkClient(String uid, String password, Boolean secure) {
        super(SECURE_API_ENDPOINT, uid, password);
    }

    public GameFrameworkClient(String uid, String password, String token, String tokenSecret) {
        super(DEFAULT_API_ENDPOINT, uid, password, token, tokenSecret);
    }

    public GameFrameworkClient(String uid, String password, String token, String tokenSecret, Boolean secure) {
        super(SECURE_API_ENDPOINT, uid, password, token, tokenSecret);
    }

    @Override
    public void login() {
        doEmptyGet(apiEndpoint + IPlayerAPI.LOGIN_PLAYER);
    }

    @Override
    public Player loginFacebook(String token) {
        return doPostRead(apiEndpoint + IPlayerAPI.LOGIN_FACEBOOK_PLAYER + "?token=" + token, PLAYER_TYPE_REFERENCE);
    }

    @Override
    public Player loginGPlus(String code) {
        return doPostRead(apiEndpoint + IPlayerAPI.LOGIN_GPLUS_PLAYER + "?code=" + code, PLAYER_TYPE_REFERENCE);
    }

    @Override
    public void activateEmail(String code) {
        doSimplePost(apiEndpoint + IPlayerAPI.ACTIVATE_PLAYER_EMAIL + "?code=" + code);
    }

    @Override
    public void requestEmailActivation() {
        doSimplePost(apiEndpoint + IPlayerAPI.REQUEST_PLAYER_EMAIL_ACTIVATION);
    }

    @Override
    public void resetPassword(String code, String password) {
        doSimplePost(apiEndpoint + IPlayerAPI.RESET_PLAYER_PASSWORD + "?code=" + code + "&password=" + encodeURL(password));
    }

    @Override
    public void requestPasswordReset(String email) {
        doSimplePost(apiEndpoint + IPlayerAPI.REQUEST_PLAYER_PASSWORD_RESET + "?email=" + encodeURL(email));
    }

    @Override
    public Player createPlayer(Player player) {
        return doPostReadObject(apiEndpoint + IPlayerAPI.CREATE_PLAYER, player, PLAYER_TYPE_REFERENCE);
    }

    @Override
    public Player readPlayer(String playerID) {
        return doSimpleGet(apiEndpoint + IPlayerAPI.READ_PLAYER.replaceAll("\\{.*\\}", playerID), PLAYER_TYPE_REFERENCE);
    }

    @Override
    public Player readPlayer(long playerID) {
        return doSimpleGet(apiEndpoint + IPlayerAPI.READ_PLAYER.replaceAll("\\{.*\\}", Long.toString(playerID)), PLAYER_TYPE_REFERENCE);
    }

    @Override
    public void deletePlayer(long playerID) {
        doSimplePost(apiEndpoint + IPlayerAPI.DELETE_PLAYER + "?playerID=" + Long.toString(playerID));
    }

    @Override
    public void updatePlayer(Player player) {
        doPostObject(apiEndpoint + IPlayerAPI.UPDATE_PLAYER, player);
    }

    @Override
    public void updatePlayerPassword(long playerID, String password) {
        doSimplePost(apiEndpoint + IPlayerAPI.UPDATE_PLAYER_PASSWORD + "?playerID=" + Long.toString(playerID)
                + "&password=" + encodeURL(password));
    }

    @Override
    public void updatePlayerEmail(long playerID, String email) {
        doSimplePost(apiEndpoint + IPlayerAPI.UPDATE_PLAYER_EMAIL + "?playerID=" + Long.toString(playerID)
                + "&email=" + encodeURL(email));
    }

    @Override
    public void updatePlayerFirstName(long playerID, String firstName) {
        doSimplePost(apiEndpoint + IPlayerAPI.UPDATE_PLAYER_FIRST_NAME + "?playerID=" + Long.toString(playerID)
                + "&firstName=" + encodeURL(firstName));
    }

    @Override
    public void updatePlayerLastName(long playerID, String lastName) {
        doSimplePost(apiEndpoint + IPlayerAPI.UPDATE_PLAYER_LAST_NAME + "?playerID=" + Long.toString(playerID)
                + "&lastName=" + encodeURL(lastName));
    }

    @Override
    public void updatePlayerFacebook(long playerID, String token) {
        doSimplePost(apiEndpoint + IPlayerAPI.UPDATE_PLAYER_FACEBOOK + "?playerID=" + Long.toString(playerID)
                + "&token=" + encodeURL(token));
    }

    @Override
    public void updatePlayerGPlus(long playerID, String code) {
        doSimplePost(apiEndpoint + IPlayerAPI.UPDATE_PLAYER_GPLUS + "?playerID=" + Long.toString(playerID)
                + "&code=" + encodeURL(code));
    }

    @Override
    public ResultsPage<Player> listPlayers(Integer pageSize, Integer pageNo) {
        return doSimpleGet(addPageSizeAndNo(apiEndpoint + IPlayerAPI.LIST_PLAYERS + "?", pageSize, pageNo), PLAYERS_RP_TYPE_REFERENCE);
    }

    @Override
    public Clue readClue(long clueID) {
        return doSimpleGet(apiEndpoint + IClueAPI.READ_CLUE.replaceAll("\\{.*\\}", Long.toString(clueID)), CLUE_TYPE_REFERENCE);
    }

    @Override
    public ResultsPage<Clue> listClues(Integer pageSize, Integer pageNo, String filter, String order) {
        return doSimpleGet(addPageSizeAndNoAndFilterAndOrder(apiEndpoint + IClueAPI.LIST_CLUES, pageSize, pageNo, encodeURL(filter), order), CLUES_RP_TYPE_REFERENCE);
    }

    @Override
    public Word readWord(long wordID) {
        return doSimpleGet(apiEndpoint + IWordAPI.READ_WORD.replaceAll("\\{.*\\}", Long.toString(wordID)), WORD_TYPE_REFERENCE);
    }

    @Override
    public ResultsPage<Word> listWords(Integer pageSize, Integer pageNo, String filter, String order) {
        return doSimpleGet(addPageSizeAndNoAndFilterAndOrder(apiEndpoint + IWordAPI.LIST_WORDS, pageSize, pageNo, encodeURL(filter), order), WORDS_RP_TYPE_REFERENCE);
    }

    @Override
    public Feedback createFeedback(long clueID) {
        return doPostRead(apiEndpoint + IFeedbackAPI.CREATE_FEEDBACK + "?clueID=" + Long.toString(clueID), FEEDBACK_TYPE_REFERENCE);
    }

    @Override
    public void postFeedback(long feedbackID, int attributePosition, String attributeValue, String comment) {
        String url = apiEndpoint + IFeedbackAPI.POST_FEEDBACK + "?feedbackID=" + Long.toString(feedbackID)
                + "&attributePosition=" + Integer.toString(attributePosition);
        if (null != attributeValue) {
            url = url + "&attributeValue=" + encodeURL(attributeValue);
        }
        if (null != comment) {
            url = url + "&comment=" + encodeURL(comment);
        }
        doSimplePost(url);
    }

    @Override
    public void cancelFeedback(long feedbackID) {
        doSimplePost(apiEndpoint + IFeedbackAPI.CANCEL_FEEDBACK + "?feedbackID=" + Long.toString(feedbackID));
    }
}