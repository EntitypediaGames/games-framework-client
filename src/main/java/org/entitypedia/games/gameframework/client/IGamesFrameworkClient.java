package org.entitypedia.games.gameframework.client;

import org.entitypedia.games.common.client.IGamesCommonClient;
import org.entitypedia.games.gameframework.common.api.IClueAPI;
import org.entitypedia.games.gameframework.common.api.IFeedbackAPI;
import org.entitypedia.games.gameframework.common.api.IPlayerAPI;
import org.entitypedia.games.gameframework.common.api.IWordAPI;

/**
 * @author <a href="http://autayeu.com/">Aliaksandr Autayeu</a>
 */
public interface IGamesFrameworkClient extends IGamesCommonClient, IPlayerAPI, IWordAPI, IClueAPI, IFeedbackAPI {
}
