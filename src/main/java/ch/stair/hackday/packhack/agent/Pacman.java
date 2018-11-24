package ch.stair.hackday.packhack.agent;

import ch.stair.hackday.packhack.analytics.AnalyticsUtils;
import ch.stair.hackday.packhack.analytics.TacticCenter;
import ch.stair.hackday.packhack.dto.Direction;
import ch.stair.hackday.packhack.dto.FieldTypes;
import ch.stair.hackday.packhack.dto.GameState;
import ch.stair.hackday.packhack.dto.PublicPlayer;
import ch.stair.hackday.packhack.player.Player;
import ch.stair.hackday.packhack.player.PlayerColor;
import ch.stair.hackday.packhack.player.PlayerState;

public class Pacman implements Agent {

    private AnalyticsUtils analyticsUtils;
    private TacticCenter tacticCenter = new TacticCenter();

    private PlayerColor myColor;
    private PlayerColor enemyColor;



    public void prepareNextStep(GameState state) {
        assert state != null;
        FieldTypes [][] actualGameField = state.getGameField();


        PublicPlayer[] publicPlayers = state.getPublicPlayers();
        int enemyID = state.getAgentID();
        int agentID = state.getAgentID()==0 ? 1 : 0;

        Player myself = transformPlayer(publicPlayers[agentID]);
        Player enemy = transformPlayer(publicPlayers[enemyID]);


        if(analyticsUtils == null) {
            analyticsUtils = new AnalyticsUtils(actualGameField);
            myColor = evaluateColour(myself);
            enemyColor = evaluateColour(enemy);
        }

        myself.setColor(myColor);
        enemy.setColor(enemyColor);

        analyticsUtils.nextStepInformation(actualGameField, myself, enemy);
    }

    private PlayerColor evaluateColour(Player player) {
        if(player.getPosX() > 10) {
            return PlayerColor.BLUE;
        } else {
            return PlayerColor.RED;
        }
    }


    private Player transformPlayer(PublicPlayer publicPlayer) {
        return new Player((int) publicPlayer.getPosition()[0],
                (int) publicPlayer.getPosition()[1], transformState(publicPlayer), null);
    }

    private PlayerState transformState(PublicPlayer publicPlayer) {
        if(publicPlayer.getIsPacman() && !publicPlayer.isWeakened()) {
            return PlayerState.PACMAN;
        } else if(!publicPlayer.getIsPacman()) {
            return PlayerState.GOST;
        } else if(publicPlayer.getIsPacman() && publicPlayer.isWeakened()) {
            return PlayerState.IMMORTAL;
        }
        throw new IllegalStateException("Player State Transformers is invalid :(");
    }

    @Override
    public String getAgentInformation() {
        return "Paci Pac";
    }

    @Override
    public Direction chooseAction() {
        switch (tacticCenter.getTactic()) {
            case ESCAPE_MODE:
                return getDirectionForEscapeMode();
            case COLLECTOR_MODE:
                return getDirectionForCollectorMode();
            case COUNTER_MEASURES:
                return getDirectionForCounterMeasures();
            default:
                return Direction.STOP;
        }
    }

    private Direction getDirectionForEscapeMode() {
        return Direction.STOP;
    }

    private Direction getDirectionForCounterMeasures() {
        return Direction.STOP;
    }

    private Direction getDirectionForCollectorMode() {
        return Direction.STOP;
    }
}
