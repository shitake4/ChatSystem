package Entity;

public class AccountInfo {
    //フィールド
    private int accountId = 0;
    private String name = null;
    private int adminFlg = 0;
    
    //メソッド
	public int getAccountId() {
		return accountId;
	}
	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAdminFlg() {
		return adminFlg;
	}
	public void setAdminFlg(int adminFlg) {
		this.adminFlg = adminFlg;
	}
}