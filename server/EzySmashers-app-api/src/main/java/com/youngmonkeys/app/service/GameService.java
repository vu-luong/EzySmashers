package com.youngmonkeys.app.service;

import com.tvd12.ezyfoxserver.entity.EzyUser;
import com.tvd12.gamebox.entity.MMOPlayer;
import com.tvd12.gamebox.entity.NormalRoom;
import com.tvd12.gamebox.entity.Player;
import com.youngmonkeys.app.game.GameRoom;

import java.util.List;

public interface GameService {
	NormalRoom removePlayer(String username);
	
	void addPlayer(MMOPlayer player);
	
	GameRoom newGameRoom(EzyUser user);
	
	MMOPlayer getPlayer(String playerName);
	
	List<String> getRoomPlayerNames(NormalRoom room);
	
	void addRoom(NormalRoom room);
	
	List<Long> getMMORoomIdList();
	
	NormalRoom getCurrentRoom(String playerName);
	
	Player getMaster(NormalRoom currentRoom);
	
	void playerJoinMMORoom(String playerName, long roomId);
}
