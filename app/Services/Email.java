package Services;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClientBuilder;
import com.amazonaws.services.simpleemail.model.*;

import java.util.Date;

public class Email
{
    public static void sendEmail(Date date)
    {
        String sender = "cdstovall@gmail.com";
        String receiver = "cdstovall@gmail.com";

        String subject = "Here are the directions for your park visit!";

        String htmlBody = "<h1>Park Visit</h1><p>" + date + "</p>";

        String textBody = "Park Visit " + date ;

        try
        {
            AmazonSimpleEmailService client = AmazonSimpleEmailServiceClientBuilder
                    .standard()
                    .withRegion(Regions.US_EAST_1)
                    .build();

            SendEmailRequest request = new SendEmailRequest()
                    .withDestination(
                            new Destination().withToAddresses(receiver))
                    .withMessage(new Message()
                            .withBody(new Body()
                                    .withHtml(new Content()
                                            .withCharset("UTF-8").withData(htmlBody))
                                    .withText(new Content()
                                            .withCharset("UTF-8").withData(textBody)))
                            .withSubject(new Content()
                                    .withCharset("UTF-8").withData(subject)))
                    .withSource(sender);

            client.sendEmail(request);
            System.out.println("Email sent");
        }
        catch(Exception e)
        {
            System.out.println("Error sending email: " + e.getMessage());
        }

    }
}
