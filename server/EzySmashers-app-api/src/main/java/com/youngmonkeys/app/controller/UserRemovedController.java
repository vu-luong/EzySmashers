package com.youngmonkeys.app.controller;

import com.tvd12.ezyfox.bean.annotation.EzyAutoBind;
import com.tvd12.ezyfox.bean.annotation.EzySingleton;
import com.tvd12.ezyfox.core.annotation.EzyEventHandler;
import com.tvd12.ezyfoxserver.context.EzyAppContext;
import com.tvd12.ezyfoxserver.controller.EzyAbstractAppEventController;
import com.tvd12.ezyfoxserver.event.EzyUserRemovedEvent;
import com.tvd12.ezyfoxserver.support.factory.EzyResponseFactory;
import com.tvd12.gamebox.entity.NormalRoom;
import com.youngmonkeys.app.constant.Commands;
import com.youngmonkeys.app.service.GameService;

import java.util.List;

import static com.tvd12.ezyfoxserver.constant.EzyEventNames.USER_REMOVED;

@EzySingleton
@EzyEventHandler(USER_REMOVED)
public class UserRemovedController
		extends EzyAbstractAppEventController<EzyUserRemovedEvent> {
	
	@EzyAutoBind
	private GameService gameService;
	
	@EzyAutoBind
	private EzyResponseFactory responseFactory;
	
	@Override
	public void handle(EzyAppContext ctx, EzyUserRemovedEvent event) {
		logger.info("EzySmashers app: user {} removed", event.getUser());
		String playerName = event.getUser().getName();
		NormalRoom room = gameService.removePlayer(playerName);
		
		if (room == null) {
			return;
		}
		
		List<String> playerNames = gameService.getRoomPlayerNames(room);
		
		responseFactory.newObjectResponse()
				.command(Commands.PLAYER_EXIT_GAME)
				.param("playerName", playerName)
				.usernames(playerNames)
				.execute();
	}
}
