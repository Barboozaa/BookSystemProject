package com.company.notequeueconsumer;

import com.company.notequeueconsumer.util.feign.NoteClient;
import com.company.notequeueconsumer.util.messages.Note;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageListener {

    @Autowired
    NoteClient client;

    @RabbitListener(queues = NoteQueueConsumerApplication.QUEUE_NAME)
    public void receiveMessage(Note note) {
        System.out.println("Note received");
        System.out.println(note.toString());
        if (note.getNoteId() == 0) {
            System.out.println("No id found, sending to add method");
            client.addNote(note);
        } else {
            System.out.println("Id found, sending to update method");
            client.updateNote(note, note.getNoteId());
        }
    }
}
