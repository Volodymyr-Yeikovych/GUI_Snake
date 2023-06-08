package model.listener;

import model.event.SaveButtonClickedEvent;

import java.util.EventListener;

public interface SaveButtonClickedListener extends EventListener {

    void saveButtonClicked(SaveButtonClickedEvent evt);
}
