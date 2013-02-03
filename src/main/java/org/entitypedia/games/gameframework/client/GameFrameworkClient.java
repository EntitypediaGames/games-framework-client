package org.entitypedia.games.gameframework.client;

import com.fasterxml.jackson.core.type.TypeReference;
import org.entitypedia.games.common.client.WordGameClient;
import org.entitypedia.games.common.model.ResultsPage;
import org.entitypedia.games.gameframework.common.api.IPlayerAPI;
import org.entitypedia.games.gameframework.common.model.Player;

/**
 * @author <a rel="author" href="http://autayeu.com/">Aliaksandr Autayeu</a>
 */
public class GameFrameworkClient extends WordGameClient implements IGameFrameworkClient {

    private static final TypeReference<Player> PLAYER_TYPE_REFERENCE = new TypeReference<Player>() {
    };
    private static final TypeReference<ResultsPage<Player>> PLAYERS_RP_TYPE_REFERENCE = new TypeReference<ResultsPage<Player>>() {
    };

    private static final String DEFAULT_API_ENDPOINT = "http://localhost:9632/gameframework/webapi/";
    private static final String SECURE_API_ENDPOINT = "https://localhost:9633/gameframework/webapi/";

    public GameFrameworkClient(String uid, String password) {
        super(DEFAULT_API_ENDPOINT, uid, password);
    }

    public GameFrameworkClient(String uid, String password, Boolean secure) {
        super(SECURE_API_ENDPOINT, uid, password);
    }

    @Override
    public void login() {
        doEmptyGet(apiEndpoint + IPlayerAPI.LOGIN_PLAYER);
    }

    @Override
    public Player loginFacebook(String token) {
        return doSimpleGet(apiEndpoint + IPlayerAPI.LOGIN_FACEBOOK_PLAYER + "?token=" + token, PLAYER_TYPE_REFERENCE);
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
    public ResultsPage<Player> listPlayers(Integer pageSize, Integer pageNo) {
        return doSimpleGet(addPageSizeAndNo(apiEndpoint + IPlayerAPI.LIST_PLAYERS + "?", pageSize, pageNo), PLAYERS_RP_TYPE_REFERENCE);
    }
}