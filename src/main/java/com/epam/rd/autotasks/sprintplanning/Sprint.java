package com.epam.rd.autotasks.sprintplanning;

import com.epam.rd.autotasks.sprintplanning.tickets.Bug;
import com.epam.rd.autotasks.sprintplanning.tickets.Ticket;
import com.epam.rd.autotasks.sprintplanning.tickets.UserStory;
import java.util.Arrays;

public class Sprint {

    private final int capacity;
    private final int ticketsLimit;
    private int ticketCounter;
    private final Ticket[] tickets;
    private int totalEstimates;

    public Sprint(int capacity, int ticketsLimit) {
        this.capacity = capacity;
        this.ticketsLimit = ticketsLimit;
        ticketCounter = 0;
        tickets = new Ticket[ticketsLimit];
        this.totalEstimates = 0;
    }

    public boolean addUserStory(UserStory userStory) {
        if (userStory == null ||
                userStory.isCompleted() ||
                (getTotalEstimate() + userStory.getEstimate()) > capacity ||
                (ticketCounter >= ticketsLimit)) {
            return false;
        }
        if (userStory.getDependencies() != null) {
            for (UserStory us : userStory.getDependencies()) {
                if (us.isCompleted() || !Arrays.asList(tickets).contains(us)) {
                    return false;
                }
            }
            tickets[ticketCounter] = userStory;
            ticketCounter++;
            totalEstimates += userStory.getEstimate();
            return true;
        }
        tickets[ticketCounter] = userStory;
        ticketCounter++;
        totalEstimates += userStory.getEstimate();
        return true;
    }

    public boolean addBug(Bug bugReport) {
        if (bugReport == null ||
                bugReport.isCompleted() ||
                (getTotalEstimate() + bugReport.getEstimate()) > capacity ||
                (ticketCounter >= ticketsLimit)) {
            return false;
        }
        tickets[ticketCounter] = bugReport;
        ticketCounter++;
        totalEstimates += bugReport.getEstimate();
        return true;
    }

    public Ticket[] getTickets() {
        return Arrays.copyOf(tickets,ticketCounter);
    }

    public int getTotalEstimate() {
        return totalEstimates;
    }
}
