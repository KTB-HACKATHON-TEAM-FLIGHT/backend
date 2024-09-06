package team.flight.backend.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import team.flight.backend.global.exception.AppException;
import team.flight.backend.global.exception.ErrorCode;

@Service
@RequiredArgsConstructor
public class PdfService {

    public File generatePdfFromHtml(String htmlContent, String outputPdfPath) throws IOException, InterruptedException {
        // HTML 내용을 임시 파일에 저장
        File htmlFile = File.createTempFile("temp-html", ".html");
        try (OutputStream outputStream = new FileOutputStream(htmlFile)) {
            outputStream.write(htmlContent.getBytes());
        }
        ProcessBuilder processBuilder = new ProcessBuilder();
        processBuilder.command("/usr/bin/wkhtmltopdf", "--page-width", "1600px", "--page-height", "900px",
                htmlFile.getAbsolutePath(), outputPdfPath);

        // 환경 변수 설정 (필요한 경우)
        processBuilder.environment().put("PATH", "/usr/bin:/bin:/usr/sbin:/sbin");

        Process process = processBuilder.start();
        int exitCode = process.waitFor();

        if (exitCode == 0) {
            return new File(outputPdfPath); // 생성된 PDF 파일 반환
        } else {
            throw new AppException(ErrorCode.PDF_CONVERT_EXCEPTION);
        }
    }
}
