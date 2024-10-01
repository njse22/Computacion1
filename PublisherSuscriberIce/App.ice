module Demo {

    interface Suscriber {
        void onUpdate(string msg);
    }

    interface Publisher {
        void addSuscriber(string name, Suscriber* o);
        void removeSuscriber(string name);
    }
}
