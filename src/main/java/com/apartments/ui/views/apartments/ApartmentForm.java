package com.apartments.ui.views.apartments;

import com.apartments.service.apartments.Apartment;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.shared.Registration;
import lombok.Getter;

public class ApartmentForm extends FormLayout {

    private Apartment apartment;

    TextField name = new TextField("Nazwa");
    TextArea description = new TextArea("Opis");
    TextField location = new TextField("Lokalizacja (Opis)");
    TextField locationSrc = new TextField("Lokalizacja (Google Maps)");
    IntegerField measurement = new IntegerField("Metraż");
    IntegerField numberOfRooms = new IntegerField("Liczba pokoi");
    TextField price = new TextField("Cena");
    TextField mainImage = new TextField("Główne zdjęcie");
    TextField image1 = new TextField("Zdjęcie nr 1");
    TextField image2 = new TextField("Zdjęcie nr 2");
    TextField image3 = new TextField("Zdjęcie nr 3");
    TextField image4 = new TextField("Zdjęcie nr 4");
    TextField image5 = new TextField("Zdjęcie nr 5");
    TextField image6 = new TextField("Zdjęcie nr 6");
    TextField image7 = new TextField("Zdjęcie nr 7");
    TextField image8 = new TextField("Zdjęcie nr 8");
    TextField image9 = new TextField("Zdjęcie nr 9");

    Binder<Apartment> binder = new Binder<>(Apartment.class);

    Button save = new Button("Zapisz");
    Button delete = new Button("Usuń");
    Button close = new Button("Zamknij");

    public ApartmentForm() {
        addClassName("apartment-form");
        binder.bindInstanceFields(this);

        add(name, description, location, locationSrc, measurement, numberOfRooms, price,
                mainImage, image1, image2, image3, image4, image5, image6, image7, image8, image9,
                createButtonsLayout());
    }

    private HorizontalLayout createButtonsLayout() {
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        delete.addThemeVariants(ButtonVariant.LUMO_ERROR);
        close.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

        save.addClickShortcut(Key.ENTER);
        close.addClickShortcut(Key.ESCAPE);

        save.addClickListener(event -> validateAndSave());
        delete.addClickListener(event -> fireEvent(new DeleteEvent(this, apartment)));
        close.addClickListener(event -> fireEvent(new CloseEvent(this)));

        binder.addStatusChangeListener(e -> save.setEnabled(binder.isValid()));

        return new HorizontalLayout(save, delete, close);
    }

    public void setApartmentBean(Apartment apartment) {
        this.apartment = apartment;
        binder.readBean(apartment);
    }

    private void validateAndSave() {
        try {
            binder.writeBean(apartment);
            fireEvent(new SaveEvent(this, apartment));
        } catch (ValidationException e) {
            e.printStackTrace();
        }
    }

    @Getter
    public static abstract class ApartmentFormEvent extends ComponentEvent<ApartmentForm> {

        private final Apartment apartment;

        protected ApartmentFormEvent(ApartmentForm source, Apartment apartment) {
            super(source, false);
            this.apartment = apartment;
        }
    }

    public static class SaveEvent extends ApartmentFormEvent {
        SaveEvent(ApartmentForm source, Apartment apartment) {
            super(source, apartment);
        }
    }

    public static class DeleteEvent extends ApartmentFormEvent {
        DeleteEvent(ApartmentForm source, Apartment apartment) {
            super(source, apartment);
        }

    }

    public static class CloseEvent extends ApartmentFormEvent {
        CloseEvent(ApartmentForm source) {
            super(source, null);
        }
    }

    public <T extends ComponentEvent<?>> Registration addListener(Class<T> eventType, ComponentEventListener<T> listener) {
        return getEventBus().addListener(eventType, listener);
    }
}
