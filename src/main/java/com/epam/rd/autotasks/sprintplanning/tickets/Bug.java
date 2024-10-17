package com.epam.rd.autotasks.sprintplanning.tickets;

public class Bug extends Ticket {

    public final UserStory userStory;

    public static Bug createBug(int id, String name, int estimate, UserStory userStory) {
        if (!userStory.isCompleted() || userStory == null) {
            return null;
        } else {
        return new Bug(id, name, estimate,userStory);
        }
    }

    private Bug(int id, String name, int estimate, UserStory userStory) {
        super(id, name, estimate);
        this.userStory = userStory;
    }

    public String getUserStoryName() {
        return userStory.getName();
    }

    @Override
    public String toString() {
        return "[Bug " + getId() + "] " + getUserStoryName() + ": " + getName();
    }
}
