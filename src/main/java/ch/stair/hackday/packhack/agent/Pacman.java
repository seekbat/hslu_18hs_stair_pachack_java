package ch.stair.hackday.packhack.agent;

import ch.stair.hackday.packhack.analytics.AnalyticsUtils;
import ch.stair.hackday.packhack.dto.Direction;
import ch.stair.hackday.packhack.dto.FieldTypes;
import ch.stair.hackday.packhack.dto.GameState;
import ch.stair.hackday.packhack.dto.PublicPlayer;
import ch.stair.hackday.packhack.player.Player;

public class Pacman implements Agent {

    private AnalyticsUtils analyticsUtils;



    public void prepareNextStep(GameState state) {
        assert state != null;
        FieldTypes [][] actualGameField = state.getGameField();

        if(analyticsUtils == null) {
            analyticsUtils = new AnalyticsUtils(actualGameField);
        }
        PublicPlayer[] publicPlayers = state.getPublicPlayers();
        int agentID = state.getAgentID();
        int enemyID = state.getAgentID()==0 ? 1 : 0;

        Player myself = transformPlayer(publicPlayers[agentID]);
        Player enemy = transformPlayer(publicPlayers[enemyID]);

        analyticsUtils.prepareNextStep(actualGameField, myself, enemy);
    }

    private Player transformPlayer(PublicPlayer publicPlayer) {
        return new Player((int) publicPlayer.getPosition()[0],
                (int) publicPlayer.getPosition()[1]);
    }

    @Override
    public String getAgentInformation() {
        return "Paci Pac";
    }

    @Override
    public Direction chooseAction() {
        return analyticsUtils.getNextStep();
    }
}
