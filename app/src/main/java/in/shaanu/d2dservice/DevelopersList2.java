package in.shaanu.d2dservice;

public class DevelopersList2 {
    private String c_name;
    private String c_number;
    private String c_fee;

    public String getC_name() {
        return c_name;
    }

    public String getC_number() {
        return c_number;
    }

    public String getC_fee() {
        return c_fee;
    }

    public DevelopersList2(String c_name, String c_number, String c_fee) {
        this.c_name = c_name;
        this.c_number = c_number;
        this.c_fee = c_fee;
    }
}
