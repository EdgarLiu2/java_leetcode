package edgar.mybatis.guigu2022.query;

public class UserQuery {

	private int ageMin;
	private int ageMax;
	private String nameStartwith;
	
	/**
	 * @return the ageMin
	 */
	public int getAgeMin() {
		return ageMin;
	}
	
	/**
	 * @param ageMin the ageMin to set
	 */
	public void setAgeMin(int ageMin) {
		this.ageMin = ageMin;
	}
	
	/**
	 * @return the ageMax
	 */
	public int getAgeMax() {
		return ageMax;
	}
	
	/**
	 * @param ageMax the ageMax to set
	 */
	public void setAgeMax(int ageMax) {
		this.ageMax = ageMax;
	}
	
	/**
	 * @return the nameStartwith
	 */
	public String getNameStartwith() {
		return nameStartwith;
	}
	
	/**
	 * @param nameStartwith the nameStartwith to set
	 */
	public void setNameStartwith(String nameStartwith) {
		this.nameStartwith = nameStartwith;
	}
	
	
}
