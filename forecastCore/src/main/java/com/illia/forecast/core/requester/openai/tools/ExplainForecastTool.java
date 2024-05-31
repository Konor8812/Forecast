package com.illia.forecast.core.requester.openai.tools;

public record ExplainForecastTool(String type, Function function) implements Tool {

  public ExplainForecastTool() {
    this("function", new Function("explain_forecast",
        "Explain and sum-up received forecast",
        new Parameters("object",
            new ExplainForecastToolProperties(),
            new String[]{"date", "location", "explanation"})));
  }

  private record ExplainForecastToolProperties(Date date,
                                               Location location,
                                               Explanation explanation) implements Properties {

    public ExplainForecastToolProperties() {
      this(new Date(), new Location(), new Explanation());
    }

    private record Date(String type, String date) {

      private Date() {
        this("string", "Date as dd/MM/YY for this forecast");
      }
    }

    private record Location(String type, String description) {

      private Location() {
        this("string", "Location as `Country, city` for which this outcast is for");
      }
    }

    private record Explanation(String type, String description) {

      public Explanation() {
        this("string",
            "An explanation of forecast received as input");

      }
    }
  }
}
