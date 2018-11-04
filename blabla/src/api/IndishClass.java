package api;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class IndishClass {
int idDish;
int idIngred;

public IndishClass() {
	// TODO Auto-generated constructor stub
}
public int getIdDish() {
	return idDish;
}
public void setIdDish(int idDish) {
	this.idDish = idDish;
}
public int getIdIngred() {
	return idIngred;
}
public void setIdIngred(int idIngred) {
	this.idIngred = idIngred;
}

}
