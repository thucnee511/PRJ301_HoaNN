package org.hoann.prj301.services;

import java.util.List;
import org.hoann.prj301.dao.BookingDAO;
import org.hoann.prj301.repositories.booking.BookingDTO;

public class BookingService {

    private static BookingService instance;
    private static final BookingDAO bookingDao = BookingDAO.getInstance();

    private BookingService() {
    }

    public static BookingService getInstance() {
        return instance = instance == null ? new BookingService() : instance;
    }

    public List<BookingDTO> get() {
        return bookingDao.get();
    }
}
