package com.rsclauncher.menu;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.rsclauncher.api.Client;
import com.rsclauncher.api.GameCharacter;

import java.awt.event.ActionEvent;
import java.io.IOException;

public class PrintClientData implements MenuItem {

  private final Client client;

  public PrintClientData(Client client) {
    this.client = client;
  }

  @Override
  public String title() {
    return "Print client data";
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    try {
      final JsonFactory factory = new JsonFactory();
      final JsonGenerator generator = factory.createGenerator(System.out);

      generator.writeStartObject();

      {
        generator.writeFieldName("localPlayer");

        writeGameCharacter(generator, client.getLocalPlayer());
      }

      {
        GameCharacter[] nearbyPlayers = client.getNearbyPlayers();

        generator.writeFieldName("nearbyPlayers");
        generator.writeStartArray();

        for (int i = 0; i < nearbyPlayers.length; i++) {
          // TODO: Write null in arrays or not? Otherwise might be real huge
          if (nearbyPlayers[i] != null) {
            writeGameCharacter(generator, nearbyPlayers[i]);
          }
        }

        generator.writeEndArray();
      }

      {
        GameCharacter[] npcs = client.getNpcs();

        generator.writeFieldName("npcs");
        generator.writeStartArray();

        for (int i = 0; i < npcs.length; i++) {
          // TODO: Write null in arrays or not? Otherwise might be real huge
          if (npcs[i] != null) {
            writeGameCharacter(generator, npcs[i]);
          }
        }

        generator.writeEndArray();
      }

      generator.writeEndObject();
      generator.flush();

      System.out.println();
    } catch (Exception ex) {
      ex.printStackTrace(System.err);
    }
  }

  private void writeGameCharacter(JsonGenerator generator, GameCharacter gameCharacter) throws IOException {
    if (gameCharacter != null) {
      generator.writeStartObject();

      generator.writeStringField("name", gameCharacter.getName());
      generator.writeNumberField("combatLevel", gameCharacter.getCombatLevel());
      generator.writeNumberField("maxHealth", gameCharacter.getMaxHealth());
      generator.writeNumberField("currentHealth", gameCharacter.getCurrentHealth());
      generator.writeNumberField("npcId", gameCharacter.getNpcId());
      generator.writeStringField("overheadMessage", gameCharacter.getOverheadMessage());

      generator.writeFieldName("equippedItems");
      generator.writeArray(gameCharacter.getEquippedItems(), 0, gameCharacter.getEquippedItems().length);

      generator.writeEndObject();
    } else {
      generator.writeNull();
    }
  }

}
