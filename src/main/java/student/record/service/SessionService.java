package student.record.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;

@Component
public class SessionService {

    private static final String SESSION_GOOGLE_AUTHORIZE_CODE = "googleAuthorizeCode";

    @Autowired
    private HttpSession httpSession;


    public void addGoogleAuthorizeCode(String code) {
        httpSession.removeAttribute(SESSION_GOOGLE_AUTHORIZE_CODE);
        httpSession.setAttribute(SESSION_GOOGLE_AUTHORIZE_CODE, code);
    }

    public String getGoogleAuthorizeCode() {
        return (String) httpSession.getAttribute(SESSION_GOOGLE_AUTHORIZE_CODE);
    }

}
