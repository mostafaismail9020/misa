package com.sap.ibso.eservices.storefront.forms;

public class SagiaResetPwdForm {
    private String pwd;
    private String checkPwd;
    private String token;


    /**
     * @return the pwd
     */
    public String getPwd()
    {
        return pwd;
    }

    /**
     * @param pwd
     *           the pwd to set
     */
    public void setPwd(final String pwd)
    {
        this.pwd = pwd;
    }

    /**
     * @return the checkPwd
     */
    public String getCheckPwd()
    {
        return checkPwd;
    }

    /**
     * @param checkPwd
     *           the checkPwd to set
     */
    public void setCheckPwd(final String checkPwd)
    {
        this.checkPwd = checkPwd;
    }

    /**
     * @return the token
     */
    public String getToken()
    {
        return token;
    }

    /**
     * @param token
     *           the token to set
     */
    public void setToken(final String token)
    {
        this.token = token;
    }
}
