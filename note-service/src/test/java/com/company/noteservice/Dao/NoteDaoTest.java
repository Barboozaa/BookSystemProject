package com.company.noteservice.Dao;

/**
 * Created by ahmedkaahin on 10/2/19.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class NoteDaoTest {

    @Autowired
    private NoteDao noteDao;
    private Note note;

    @Before
    public void setUp() throws Exception {
        noteDao.getAllNotes().forEach(note -> {
            noteDao.deleteNote(note.getNoteId());
        });

        note = new Note();
        note.noteId(1);
        note.bookId(2);
        note.note("The best book go check");

        note1 = new Note();
        note1.noteId(1);
        note1.bookId(2);
        note1.note("The next best book next check ");


        @Test
        public void addNote () {
            note = noteDao.addNote(note);
            assertEquals(1, noteDao.getAllNote().size());
        }

        @Test
        public void getNote () {
         =noteDao.addNote(note);
            Note notetest = noteDao.getNote(note.getNoteId());
            assertEquals(noteTest, note);

        }

        @Test
        public void updateNote () {
            note = noteDao.addNote(note);
            note.note_id(1);
            note.book_id(2);
            noteDao.updateNote(note);
            Note noteTest = noteDao.getNote(note.getNoteId());
            assertEquals(noteTest, note);
        }

        @Test
        public void deleteNote () {
            note = noteDao.addNote(note);
            noteDao.deleteNote(note.getNoteId());
            Note noteTest = noteDao.getNote(note.getNoteId());
            assertNull(noteTest);
        }

        @Test
        public void getAllNotes () {

            noteDao.addNote(note);
            noteDao.addNote(note1);
            List<Note> notes = noteDao.getAllNotes();
            assertEquals(2, notes.size());

        }


    }
}
