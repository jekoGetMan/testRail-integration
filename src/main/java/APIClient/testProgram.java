package APIClient;

public class testProgram {
    public static void main(String[] args) throws Exception {

        API_Client getClient = new API_Client("https://testrail.refinitiv.com/testrail", "login", "password");
        System.out.println(getClient.sendGet("get_case/31477741"));

    }
}