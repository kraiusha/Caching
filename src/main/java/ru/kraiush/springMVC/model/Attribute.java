package ru.kraiush.springMVC.model;

public class Attribute {
	
	private int feature_01;	
	private int feature_02;		
	private boolean uf_01;
	private boolean uf_02;	
	
	public int getFeature_01() {
		return feature_01;
	}

	public void setFeature_01(int feature_01) {
		this.feature_01 = feature_01;
	}
	
	public int getFeature_02() {
		return feature_02;
	}

	public void setFeature_02(int feature_02) {
		this.feature_02 = feature_02;
	}

	public boolean isUf_01() {
		return uf_01;
	}

	public void setUf_01(boolean uf_01) {
		this.uf_01 = uf_01;
	}

	public boolean isUf_02() {
		return uf_02;
	}

	public void setUf_02(boolean uf_02) {
		this.uf_02 = uf_02;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Attribute))
			return false;
		Attribute other = (Attribute) obj;
		return true;
	}
    
	@Override
	public String toString() {
		return "Attribute [ feature_01=" + feature_01 + " feature_02=" + feature_02  
				+ " uf_01=" + uf_01 + " uf_02=" + uf_02 + "]";
	}
}
