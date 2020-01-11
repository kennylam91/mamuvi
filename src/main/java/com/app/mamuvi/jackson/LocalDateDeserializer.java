package com.app.mamuvi.jackson;

import java.io.IOException;
import java.time.LocalDate;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

public class LocalDateDeserializer extends StdDeserializer<LocalDate>{

  
  private static final long serialVersionUID = -5689933918514412860L;

  protected LocalDateDeserializer() {
    super(LocalDate.class);
  }
  
  @Override
  public LocalDate deserialize(JsonParser p, DeserializationContext ctxt)
      throws IOException {
    return LocalDate.parse(p.readValueAs(String.class));
  }
  

}
