package com.company.noteservice.Dto;

note_id int not null auto_increment primary key,
        book_id int not null,
        note varchar(255)

    public class NoteDto {
    @min(0)
    private int note_id;
    @min(0)
    private int book_id;
    @Size(min=5, max=255)
    private String note;

    public NoteDto(){};

        public NoteDto(int note_id, int book_id, String note) {
            this.note_id = note_id;
            this.book_id = book_id;
            this.note = note;
        }

        public int getNote_id() {
            return note_id;
        }

        public void setNote_id(int note_id) {
            this.note_id = note_id;
        }

        public int getBook_id() {
            return book_id;
        }

        public void setBook_id(int book_id) {
            this.book_id = book_id;
        }

        public String getNote() {
            return note;
        }

        public void setNote(String note) {
            this.note = note;
        }
    }
