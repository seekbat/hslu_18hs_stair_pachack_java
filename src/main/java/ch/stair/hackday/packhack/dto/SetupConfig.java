package ch.stair.hackday.packhack.dto;

public class SetupConfig {
    private final String response;

    public SetupConfig(final String response){
        this.response = response;
    }

    public String getResponse() {
        return response;
    }
}
