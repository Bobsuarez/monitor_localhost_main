package co.accesspark.monitor.persistence.dto;

public class MessageWelcome
{

  private String welcome;

  public MessageWelcome()
  {
  }

  public MessageWelcome(String welcome)
  {
    this.welcome = welcome;
  }

  public String getWelcome()
  {
    return welcome;
  }

  public void setWelcome(String welcome)
  {
    this.welcome = welcome;
  }

}
