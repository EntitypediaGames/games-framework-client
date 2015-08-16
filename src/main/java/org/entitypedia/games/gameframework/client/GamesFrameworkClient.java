package org.entitypedia.games.gameframework.client;

import com.fasterxml.jackson.core.type.TypeReference;
import org.entitypedia.games.common.client.GamesCommonClient;
import org.entitypedia.games.common.model.ResultsPage;
import org.entitypedia.games.gameframework.common.api.*;
import org.entitypedia.games.gameframework.common.model.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author <a href="http://autayeu.com/">Aliaksandr Autayeu</a>
 */
public class GamesFrameworkClient extends GamesCommonClient implements IGamesFrameworkClient {

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
    private static final TypeReference<ClueTemplate> CLUE_TEMPLATE_TYPE_REFERENCE = new TypeReference<ClueTemplate>() {
    };
    private static final TypeReference<ResultsPage<ClueTemplate>> CLUE_TEMPLATES_RP_TYPE_REFERENCE = new TypeReference<ResultsPage<ClueTemplate>>() {
    };
    private static final TypeReference<Developer> DEVELOPER_TYPE_REFERENCE = new TypeReference<Developer>() {
    };
    private static final TypeReference<ResultsPage<Developer>> DEVELOPERS_RP_TYPE_REFERENCE = new TypeReference<ResultsPage<Developer>>() {
    };
    private static final TypeReference<Game> GAME_TYPE_REFERENCE = new TypeReference<Game>() {
    };
    private static final TypeReference<ResultsPage<Game>> GAMES_RP_TYPE_REFERENCE = new TypeReference<ResultsPage<Game>>() {
    };

    private static final String DEFAULT_API_ENDPOINT = "http://games.entitypedia.org/";
    private static final String SECURE_API_ENDPOINT = "https://games.entitypedia.org/";

    public GamesFrameworkClient(String uid, String password) {
        super(getDefaultAPIEndPoint(), uid, password);
    }

    public GamesFrameworkClient(String uid, String password, Boolean secure) {
        super(getSecureAPIEndPoint(), uid, password);
    }

    public GamesFrameworkClient(String uid, String password, String token, String tokenSecret) {
        super(getDefaultAPIEndPoint(), uid, password, token, tokenSecret);
    }

    public GamesFrameworkClient(String uid, String password, String token, String tokenSecret, Boolean secure) {
        super(getSecureAPIEndPoint(), uid, password, token, tokenSecret);
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

    @Override
    public void confirmClue(long clueID, double confidence) {
        doSimplePost(apiEndpoint + IFeedbackAPI.CONFIRM_CLUE + "?clueID=" + Long.toString(clueID) + "&confidence=" + Double.toString(confidence));
    }

    @Override
    public ClueTemplate readClueTemplate(long clueTemplateID) {
        return doSimpleGet(apiEndpoint + IClueTemplateAPI.READ_CLUE_TEMPLATE.replaceAll("\\{.*\\}", Long.toString(clueTemplateID)), CLUE_TEMPLATE_TYPE_REFERENCE);
    }

    @Override
    public ResultsPage<ClueTemplate> listClueTemplates(Integer pageSize, Integer pageNo, String filter, String order) {
        return doSimpleGet(addPageSizeAndNoAndFilterAndOrder(apiEndpoint + IClueTemplateAPI.LIST_CLUE_TEMPLATES, pageSize, pageNo, encodeURL(filter), order), CLUE_TEMPLATES_RP_TYPE_REFERENCE);
    }

    @Override
    public void loginDeveloper() {
        doEmptyGet(apiEndpoint + IDeveloperAPI.LOGIN_DEVELOPER);
    }

    @Override
    public Developer readDeveloper(long developerID) {
        return doSimpleGet(apiEndpoint + IDeveloperAPI.READ_DEVELOPER.replaceAll("\\{.*\\}", Long.toString(developerID)), DEVELOPER_TYPE_REFERENCE);
    }

    @Override
    public void resetDeveloperPassword(String code, String password) {
        doSimplePost(apiEndpoint + IDeveloperAPI.RESET_DEVELOPER_PASSWORD + "?code=" + code + "&password=" + encodeURL(password));
    }

    @Override
    public void requestDeveloperPasswordReset(String email) {
        doSimplePost(apiEndpoint + IDeveloperAPI.REQUEST_DEVELOPER_PASSWORD_RESET + "?email=" + encodeURL(email));
    }

    @Override
    public void updateDeveloperPassword(long developerID, String password) {
        doSimplePost(apiEndpoint + IDeveloperAPI.UPDATE_DEVELOPER_PASSWORD + "?developerID=" + Long.toString(developerID)
                + "&password=" + encodeURL(password));
    }

    @Override
    public void updateDeveloperEmail(long developerID, String email) {
        doSimplePost(apiEndpoint + IDeveloperAPI.UPDATE_DEVELOPER_EMAIL + "?developerID=" + Long.toString(developerID)
                + "&email=" + encodeURL(email));
    }

    @Override
    public void updateDeveloperFirstName(long developerID, String firstName) {
        doSimplePost(apiEndpoint + IDeveloperAPI.UPDATE_DEVELOPER_FIRST_NAME + "?developerID=" + Long.toString(developerID)
                + "&firstName=" + encodeURL(firstName));
    }

    @Override
    public void updateDeveloperLastName(long developerID, String lastName) {
        doSimplePost(apiEndpoint + IDeveloperAPI.UPDATE_DEVELOPER_LAST_NAME + "?developerID=" + Long.toString(developerID)
                + "&lastName=" + encodeURL(lastName));
    }

    @Override
    public ResultsPage<Developer> listDevelopers(Integer pageSize, Integer pageNo, String filter, String order) {
        return doSimpleGet(addPageSizeAndNoAndFilterAndOrder(apiEndpoint + IDeveloperAPI.LIST_DEVELOPERS, pageSize, pageNo, encodeURL(filter), order), DEVELOPERS_RP_TYPE_REFERENCE);
    }

    @Override
    public Game createGame() {
        return doPostRead(apiEndpoint + IGameAPI.CREATE_GAME, GAME_TYPE_REFERENCE);
    }

    @Override
    public Game readGame(long gameID) {
        return doSimpleGet(apiEndpoint + IGameAPI.READ_GAME.replaceAll("\\{.*\\}", Long.toString(gameID)), GAME_TYPE_REFERENCE);
    }

    @Override
    public void updateGameTitle(long gameID, String title) {
        doSimplePost(apiEndpoint + IGameAPI.UPDATE_GAME_TITLE + "?gameID=" + Long.toString(gameID)
                + "&title=" + encodeURL(title));
    }

    @Override
    public void updateGameDescription(long gameID, String description) {
        doSimplePost(apiEndpoint + IGameAPI.UPDATE_GAME_DESCRIPTION + "?gameID=" + Long.toString(gameID)
                + "&description=" + encodeURL(description));
    }

    @Override
    public void updateGameURL(long gameID, String url) {
        doSimplePost(apiEndpoint + IGameAPI.UPDATE_GAME_URL + "?gameID=" + Long.toString(gameID)
                + "&url=" + encodeURL(url));
    }

    @Override
    public void updateGameLogoURL(long gameID, String url) {
        doSimplePost(apiEndpoint + IGameAPI.UPDATE_GAME_LOGO_URL + "?gameID=" + Long.toString(gameID)
                + "&url=" + encodeURL(url));
    }

    @Override
    public void updateGameSliderURL(long gameID, String url) {
        doSimplePost(apiEndpoint + IGameAPI.UPDATE_GAME_SLIDER_URL + "?gameID=" + Long.toString(gameID)
                + "&url=" + encodeURL(url));
    }

    @Override
    public void updateGameOAuthCallbackURL(long gameID, String url) {
        doSimplePost(apiEndpoint + IGameAPI.UPDATE_GAME_OAUTH_CALLBACK_URL + "?gameID=" + Long.toString(gameID)
                + "&url=" + encodeURL(url));
    }

    @Override
    public void updateGameOAuthSecret(long gameID, String secret) {
        doSimplePost(apiEndpoint + IGameAPI.UPDATE_GAME_OAUTH_SECRET + "?gameID=" + Long.toString(gameID)
                + "&secret=" + encodeURL(secret));
    }

    @Override
    public void updateGameProduction(long gameID, boolean production) {
        doSimplePost(apiEndpoint + IGameAPI.UPDATE_GAME_PRODUCTION + "?gameID=" + Long.toString(gameID)
                + "&production=" + Boolean.toString(production));
    }

    @Override
    public void deleteGame(long gameID) {
        doSimplePost(apiEndpoint + IGameAPI.DELETE_GAME + "?gameID=" + Long.toString(gameID));
    }

    @Override
    public ResultsPage<Game> listGames(Integer pageSize, Integer pageNo) {
        return doSimpleGet(addPageSizeAndNo(apiEndpoint + IGameAPI.LIST_GAMES + "?", pageSize, pageNo), GAMES_RP_TYPE_REFERENCE);
    }

    private static String getDefaultAPIEndPoint() {
        return getResourcePropertiesString("gameframework.root", DEFAULT_API_ENDPOINT) + "webapi/";
    }

    private static String getSecureAPIEndPoint() {
        return getResourcePropertiesString("gameframework.secure.root", SECURE_API_ENDPOINT) + "webapi/";
    }

    private static String getResourcePropertiesString(String key, String defaultValue) {
        InputStream propStream = GamesFrameworkClient.class.getClassLoader().getResourceAsStream("games-framework.properties");
        String result = defaultValue;
        if (null != propStream) {
            Properties props = new Properties();
            try {
                props.load(propStream);
                result = props.getProperty(key);
            } catch (IOException e) {
                // nop
            }
        }
        return result;
    }
}