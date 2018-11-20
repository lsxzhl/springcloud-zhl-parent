package com.zhl.lamada.EventListenerTest;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EventTest {

    public static void main(String[] args) {

        JButton jButton = new JButton("show");

        jButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Event handling without lambda expression is boring");
            }
        });

        jButton.addActionListener((e) -> {System.out.println("Light, Camera, Action !! Lambda expressions Rocks");});

    }



}
