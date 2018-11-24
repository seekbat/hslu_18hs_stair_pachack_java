package ch.stair.hackday.packhack.player;

public enum PlayerState {
    GOST(0),PACMAN(1),IMMORTAL(2);

    private int code;
    PlayerState(int i) {
        this.code = i;
    }

    public int getCode() {
        return code;
    }
}
