package entity;

public class User
{
    private String user;
    private String password;
    private String repassword;
    private String passwordMD5;
    private String xm;
    private String email;
    private String phone;
    private String qq;
    private String validationCode;
	/**
	 * validationCode属性的getter方法
	 */
	public String getValidationCode()
	{
		return validationCode;
	}
	/**
	 * validationCode属性的setter方法
	 */
	public void setValidationCode(String validationCode)
	{
		this.validationCode = validationCode;
	}
	/**
	 * user属性的getter方法
	 */
	public String getUser()
	{
	//	System.out.println("called by RegisterAction in User: getUser(), "+user);
		return user;
	}
	/**
	 * user属性的setter方法
	 */
	public void setUser(String user)
	{
	//	System.out.println("called by RegisterAction in User: setUser(), "+user);
		this.user = user;
	}
	/**
	 * password属性的getter方法
	 */
	public String getPassword()
	{
		return password;
	}
	/**
	 * password属性的setter方法
	 */
	public void setPassword(String password)
	{
		this.password = password;
	}
	/**
	 * xm属性的getter方法
	 */
	public String getXm()
	{
		return xm;
	}
	/**
	 * xm属性的setter方法
	 */
	public void setXm(String xm)
	{
		this.xm = xm;
	}
	/**
	 * email属性的getter方法
	 */
	public String getEmail()
	{
		return email;
	}
	/**
	 * email属性的setter方法
	 */
	public void setEmail(String email)
	{
		this.email = email;
	}
	/**
	 * phone属性的getter方法
	 */
	public String getPhone()
	{
		return phone;
	}
	/**
	 * phone属性的setter方法
	 */
	public void setPhone(String phone)
	{
		this.phone = phone;
	}
	/**
	 * qq属性的getter方法
	 */
	public String getQq()
	{
		return qq;
	}
	/**
	 * qq属性的setter方法
	 */
	public void setQq(String qq)
	{
		this.qq = qq;
	}
	/**
	 * passwordMD5属性的getter方法
	 */
	public String getPasswordMD5() throws Exception
	{
		return common.Encrypter.md5Encrypt(password);
	}
	/**
	 * passwordMD5属性的setter方法
	 */
	public void setPasswordMD5(String passwordMD5)
	{
		this.passwordMD5 = passwordMD5;
	}
	
    @Override
	public String toString() {
		return "User [user=" + user + ", password=" + password + ", repassword=" + repassword + ", passwordMD5="
				+ passwordMD5 + ", xm=" + xm + ", email=" + email + ", phone=" + phone + ", qq=" + qq
				+ ", validationCode=" + validationCode + "]";
	}
	/**
	 * repassword属性的getter方法
	 */
	public String getRepassword()
	{
		return repassword;
	}
	/**
	 * repassword属性的setter方法
	 */
	public void setRepassword(String repassword)
	{
		this.repassword = repassword;
	}
	
	
}
