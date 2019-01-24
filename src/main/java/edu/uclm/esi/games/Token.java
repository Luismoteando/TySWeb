package edu.uclm.esi.games;

import edu.uclm.esi.mongolabels.labels.Bsonable;

public class Token {
  @Bsonable
  private String userName;
  @Bsonable
  private long caducidad;
  @Bsonable
  private String valor;

  public String getValor() {
    return valor;
  }

  public void setValor(String valor) {
    this.valor = valor;
  }

  public Token(String userName) {
    this.userName = userName;
    this.caducidad = System.currentTimeMillis() + 5*60*1000;
    //this.valor = new UUID.randomUUID().toString();
  }

}
