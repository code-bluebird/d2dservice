package in.shaanu.d2dservice;

public class DevelopersList {

    private String p_name;
    private String p_number;
    private String p_fee;

    public String getP_name() {
        return p_name;
    }

    public String getP_number() {
        return p_number;
    }

    public String getP_fee() {
        return p_fee;
    }

    public DevelopersList(String p_name, String p_number, String p_fee) {
        this.p_name = p_name;
        this.p_number = p_number;
        this.p_fee = p_fee;
    }
}