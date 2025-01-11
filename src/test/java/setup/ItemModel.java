package setup;

public class ItemModel {

    public ItemModel(){

    }

    private String iteamName;

    private String txxNum;

    private  String ammount;

    private String purchageDate;

    private  String month;

    private String remark;


    public String getIteamName() {
        return iteamName;
    }

    public void setIteamName(String iteamName) {
        this.iteamName = iteamName;
    }

    public String getTxxNum() {
        return txxNum;
    }

    public void setTxxNum(String txxNum) {
        this.txxNum = txxNum;
    }

    public String getAmmount() {
        return ammount;
    }

    public void setAmmount(String ammount) {
        this.ammount = ammount;
    }

    public String getPurchageDate() {
        return purchageDate;
    }

    public void setPurchageDate(String purchageDate) {
        this.purchageDate = purchageDate;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public void iteamModel(String iteamName, String txxNum, String ammount, String purchageDate, String month, String remark){

        this.iteamName=iteamName;
        this.txxNum=txxNum;
        this.ammount=ammount;
        this.purchageDate=purchageDate;
        this.month=month;
        this.remark=remark;


    }

}
