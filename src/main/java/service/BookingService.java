package service;

import dao.BookingDAO;
import dto.BookingDTO;
import dto.BookingWithCustomerDTO;
import java.util.List;

public class BookingService {
    private BookingDAO bookingDAO = new BookingDAO();

    public List<BookingDTO> getAllBookings() {
        try {
            return bookingDAO.getAllBookings();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public BookingDTO getBookingById(int id) {
        try {
            return bookingDAO.getBookingById(id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean addBooking(BookingDTO booking) {
        try {
            return bookingDAO.addBooking(booking);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<BookingWithCustomerDTO> getAllBookingsWithCustomerName() {
        try {
            return bookingDAO.getAllBookingsWithCustomerName();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean deleteBooking(int id) {
        try {
            return bookingDAO.deleteBooking(id);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateBooking(BookingDTO booking) {
        try {
            return bookingDAO.updateBooking(booking);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
} 