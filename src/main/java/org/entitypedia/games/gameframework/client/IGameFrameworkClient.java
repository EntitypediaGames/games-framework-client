package org.entitypedia.games.gameframework.client;

import org.entitypedia.games.common.client.IWordGameClient;
import org.entitypedia.games.gameframework.common.api.IClueAPI;
import org.entitypedia.games.gameframework.common.api.IPlayerAPI;
import org.entitypedia.games.gameframework.common.api.IWordAPI;

/**
 * @author <a rel="author" href="http://autayeu.com/">Aliaksandr Autayeu</a>
 */
public interface IGameFrameworkClient extends IWordGameClient, IPlayerAPI, IWordAPI, IClueAPI {
}
