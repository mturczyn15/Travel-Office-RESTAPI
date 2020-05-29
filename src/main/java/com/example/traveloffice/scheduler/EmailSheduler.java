package com.example.traveloffice.scheduler;


import com.example.traveloffice.config.AdminConfig;
import com.example.traveloffice.domain.Mail;
import com.example.traveloffice.repository.BookingRepository;
import com.example.traveloffice.service.SimpleEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class EmailSheduler {

    @Autowired
    private SimpleEmailService simpleEmailService;

    @Autowired
    private BookingRepository bookingRepository;

    private static final String SUBJECT = "Your bookings.";

    @Autowired
    private AdminConfig adminConfig;
    @Scheduled(cron = "0 0 10 * * *")
    public  void sendInformationEmail() {
        long size = bookingRepository.count();
        simpleEmailService.send(Mail.builder()
                .mailTo(adminConfig.getAmdinMail())
                .Subject(SUBJECT)
                .message("Currently in database you got: " + size + chooseMessage(size))
                .build()
        );
    }

    public static String chooseMessage(long size) {
        return size > 1 ? " bookingss " : " booking ";
    }
}
