package reader;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import entity.Person;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class TableFileReader {
    private File path;
    private Workbook workbook;
    private Sheet sheet;

    public TableFileReader() {
    }

    public TableFileReader(File path) {
        this.path = path;
        try {
            this.workbook = WorkbookFactory.create(path);
            this.sheet = workbook.getSheetAt(1);
        } catch (EncryptedDocumentException e) {
            System.err.println("TableFileReader constructor error!!!");
            e.printStackTrace();
        } catch (IOException e) {
            System.err.println("TableFileReader constructor error!!!");
            e.printStackTrace();
        }
    }

    public File getPath() {
        return path;
    }

    public void setPath(File path) {
        this.path = path;
    }

    public String GetDateFromFile() {
        try {//Защита от пустого поля
            String date = sheet.getRow(5).getCell(0)
                    .getDateCellValue().toString();
            return date.substring(date.length() - 4);
        } catch (Exception e) {
            return null;
        }
    }

    public String GetChairFromFile() {
        try {
            return sheet.getRow(5).getCell(2).getStringCellValue();
        } catch (Exception e) {
            return null;
        }
    }

    public int GetPersonCountFromFile() {
        int i = 5;
        for (; i < 31; i++) {
            sheet.getRow(i).getCell(5).getStringCellValue();
        }
        return i - 5;
    }

    public String GetNameFromFile(int rowNumber) {
        try {
            return sheet.getRow(rowNumber).getCell(5).getStringCellValue();
        } catch (Exception e) {
            return null;
        }
    }

    public String GetRankFromFile(int rowNumber) {
        try {
            return sheet.getRow(rowNumber).getCell(4).getStringCellValue();
        } catch (Exception e) {
            return null;
        }
    }

    public double GetRatingFromFile(int rowNumber) {
        try {
            System.out.println("All right!");
            return Math.round(sheet.getRow(rowNumber).getCell(154)
                    .getNumericCellValue() * 100.00) / 100.00;
        } catch (Exception e) {
            return (Double) null;
        }
    }

    public double GetQualRateFromFile(int rowNumber) {
        try {
            return Math.round(sheet.getRow(rowNumber).getCell(152).getNumericCellValue() * 100.00) / 100.00;
        } catch (Exception e) {
            return (Double) null;
        }
    }

    public String GetCategoryFromFile(int rowNumber) {
        try {
            if ((int) sheet.getRow(rowNumber).getCell(8)
                    .getNumericCellValue() == 1) {
                return "нач. кафедры";
            } else if ((int) sheet.getRow(rowNumber).getCell(9)
                    .getNumericCellValue() == 1) {
                return "нач. кафедры";//при отсутствии по штату должности заместителя
            } else if ((int) sheet.getRow(rowNumber).getCell(10)
                    .getNumericCellValue() == 1) {
                return "зам. нач. кафедры";
            } else if ((int) sheet.getRow(rowNumber).getCell(11)
                    .getNumericCellValue() == 1) {
                return "нач. цикла";//профессор
            } else if ((int) sheet.getRow(rowNumber).getCell(12)
                    .getNumericCellValue() == 1) {
                return "нач. цикла";
            } else if ((int) sheet.getRow(rowNumber).getCell(13)
                    .getNumericCellValue() == 1) {
                return "профессор";
            } else if ((int) sheet.getRow(rowNumber).getCell(14)
                    .getNumericCellValue() == 1) {
                return "доцент";
            } else if ((int) sheet.getRow(rowNumber).getCell(15)
                    .getNumericCellValue() == 1) {
                return "ст. преподаватель";
            } else if ((int) sheet.getRow(rowNumber).getCell(16)
                    .getNumericCellValue() == 1) {
                return "преподаватель";
            } else {
                System.err.println("NotAnError: GetCategory unknown type.");
                return null;
            }
        } catch (Exception e) {
            return null;
        }
    }

    private int GetRowByPerson(Person p) {
        int row;

        try {
            for (row = 5; row < 31; row++) {
                if (sheet.getRow(row).getCell(5).getStringCellValue()
                        .equals(p.getpName())) {
                    return row;
                }
            }
        } catch (Exception e) {
            System.err.println("GetCategory unknown error!!!");
            return 0;
        }
        return 0;
    }

    public int[] GetAdditionalInfo1(Person p) {
        int[] infoArray = new int[10];
        int pRowNumber = GetRowByPerson(p);
        //доктор наук
        try {
            if ((int) sheet.getRow(pRowNumber).getCell(19)
                    .getNumericCellValue() == 1) {
                infoArray[0] = 1;
            } else {
                infoArray[0] = 0;
            }

        } catch (Exception e) {
            infoArray[0] = -1;
        }

        //кандидат наук
        try {
            if ((int) sheet.getRow(pRowNumber).getCell(20)
                    .getNumericCellValue() == 1) {
                infoArray[1] = 1;
            } else {
                infoArray[1] = 0;
            }

        } catch (Exception e) {
            infoArray[1] = -1;
        }

        //профессор
        try {
            if (((int) sheet.getRow(pRowNumber).getCell(8)
                    .getNumericCellValue() == 1) || ((int) sheet
                    .getRow(pRowNumber).getCell(9).getNumericCellValue() == 1)
                    || ((int) sheet.getRow(pRowNumber).getCell(11)
                    .getNumericCellValue() == 1) || ((int) sheet
                    .getRow(pRowNumber).getCell(13).getNumericCellValue()
                    == 1)) {
                infoArray[2] = 1;
            } else {
                infoArray[2] = 0;
            }
        } catch (Exception e) {
            infoArray[2] = -1;
        }

        //доцент
        try {
            if (((int) sheet.getRow(pRowNumber).getCell(10).getNumericCellValue() == 1) || ((int) sheet.getRow(pRowNumber).getCell(12).getNumericCellValue() == 1) || ((int) sheet.getRow(pRowNumber).getCell(14).getNumericCellValue() == 1)) {
                infoArray[3] = 1;
            } else {
                infoArray[3] = 0;
            }

        } catch (Exception e) {
            infoArray[3] = -1;
        }

        //магистр
        try {
            if ((int) sheet.getRow(pRowNumber).getCell(23)
                    .getNumericCellValue() == 1) {
                infoArray[4] = 1;
            } else {
                infoArray[4] = 0;
            }

        } catch (Exception e) {
            infoArray[4] = -1;
        }


        try {
            int sum = 0;
            if ((int) sheet.getRow(pRowNumber).getCell(10)
                    .getNumericCellValue() == 1) {
                sum = sum + 1;
            }
            if ((int) sheet.getRow(pRowNumber).getCell(10)
                    .getNumericCellValue() == 1) {
                sum = sum + 1;
            }
            if ((int) sheet.getRow(pRowNumber).getCell(10)
                    .getNumericCellValue() == 1) {
                sum = sum + 1;
            }
            infoArray[5] = sum;

        } catch (Exception e) {
            infoArray[5] = -1;
        }
        ////////
        //уровень ппп
        try {
            infoArray[6] = (int) sheet.getRow(pRowNumber)
                    .getCell(46).getNumericCellValue();
        } catch (Exception e) {
            infoArray[6] = -1;//Ошибка получения информации
        }

        //наличие квалификации пвш
        try {
            if ((int) sheet.getRow(pRowNumber).getCell(27)
                    .getNumericCellValue() == 1) {
                infoArray[7] = 1;
            } else {
                infoArray[7] = 0;
            }

        } catch (Exception e) {
            infoArray[7] = -1;
            // FileException.callAlert("Некорректная информация касательно наличия уровня ПВШ!");
        }

        //прохождение квалификации ппс
        try {
            if ((int) sheet.getRow(pRowNumber).getCell(28)
                    .getNumericCellValue() == 1) {
                infoArray[8] = 1;
            } else {
                infoArray[8] = 0;
            }

        } catch (Exception e) {
            infoArray[8] = -1;
            //FileException.callAlert("Некорректная информация касательно квалификации ППС!");
        }

        //обучение в форме...
        try {
            if (((int) sheet.getRow(pRowNumber).getCell(29).getNumericCellValue() == 1) || ((int) sheet.getRow(pRowNumber).getCell(30).getNumericCellValue() == 1) || ((int) sheet.getRow(pRowNumber).getCell(31).getNumericCellValue() == 1)) {
                infoArray[9] = 1;
            } else {
                infoArray[9] = 0;
            }

        } catch (Exception e) {
            infoArray[9] = -1;
            System.err.println("GetAdditionalInfo error: 10 parameter!");
        }

        return infoArray;
    }
    ///////////////////////

    public double[] GetAdditionalInfo2(Person p) {//вызов по объекту данного класса с соответствующим файлом, переданным в конструктор
        double[] infoArray = new double[14];
        int pRowNumber = GetRowByPerson(p);

        /*1
         * 43 столбец*/
        try {
            infoArray[0] = Math.round(sheet.getRow(pRowNumber).getCell(51)
                    .getNumericCellValue() * 100.00) / 100.00;
        } catch (Exception e) {
            infoArray[0] = -1;//Ошибка получения информации
            //FileException.callAlert("Некорректная информация в 43-ем столбце!");
        }
        /*
         * 2
         * 46*/
        try {
            infoArray[1] = Math.round(sheet.getRow(pRowNumber).getCell(55)
                    .getNumericCellValue() * 100.00) / 100.00;
        } catch (Exception e) {
            infoArray[1] = -1;//Ошибка получения информации
            //FileException.callAlert("Некорректная информация в 46-ом столбце!");
        }

        /*
         * 3
         * 50*/
        try {
            infoArray[2] = Math.round(sheet.getRow(pRowNumber).getCell(62)
                    .getNumericCellValue() * 100.00) / 100.00;
        } catch (Exception e) {
            infoArray[2] = -1;//Ошибка получения информации
            //FileException.callAlert("Некорректная информация в 50-ом столбце!");
        }

        /*
         * 4
         * 47*/
        try {
            infoArray[3] = Math.round(sheet.getRow(pRowNumber).getCell(59)
                    .getNumericCellValue() * 100.00) / 100.00;
        } catch (Exception e) {
            infoArray[3] = -1;//Ошибка получения информации
            //FileException.callAlert("Некорректная информация в 47-ом столбце!");
        }
        /*
         * 5
         * 49*/
        try {
            infoArray[4] = Math.round(sheet.getRow(pRowNumber).getCell(61)
                    .getNumericCellValue() * 100.00) / 100.00;
        } catch (Exception e) {
            infoArray[4] = -1;//Ошибка получения информации
            //FileException.callAlert("Некорректная информация в 49-ом столбце!");
        }


        /*
         * 6
         * 70*/
        try {
            infoArray[5] = Math.round(sheet.getRow(pRowNumber).getCell(94)
                    .getNumericCellValue() * 100.00) / 100.00;
        } catch (Exception e) {
            infoArray[5] = -1;//Ошибка получения информации
            //FileException.callAlert("Некорректная информация в 70-ом столбце!");
        }
        /*
         * 7
         * 72*/
        try {
            infoArray[6] = Math.round(sheet.getRow(pRowNumber).getCell(100)
                    .getNumericCellValue() * 100.00) / 100.00;
        } catch (Exception e) {
            infoArray[6] = -1;//Ошибка получения информации
            //FileException.callAlert("Некорректная информация в 72-ом столбце!");
        }
        /*
         * 8
         * 57*/
        try {
            infoArray[7] = Math.round(sheet.getRow(pRowNumber).getCell(78)
                    .getNumericCellValue() * 100.00) / 100.00;
        } catch (Exception e) {
            infoArray[7] = -1;//Ошибка получения информации
            //FileException.callAlert("Некорректная информация в 57-ом столбце!");
        }
        /*
         * 9
         * 60*/
        try {
            infoArray[8] = Math.round(sheet.getRow(pRowNumber).getCell(81)
                    .getNumericCellValue() * 100.00) / 100.00;
        } catch (Exception e) {
            infoArray[8] = -1;//Ошибка получения информации
            //FileException.callAlert("Некорректная информация 60-ом столбце!");
        }
        /*
         * 10
         * 78*/
        try {
            infoArray[9] = Math.round(sheet.getRow(pRowNumber).getCell(107)
                    .getNumericCellValue() * 100.00) / 100.00;
        } catch (Exception e) {
            infoArray[9] = -1;//Ошибка получения информации
            //FileException.callAlert("Некорректная информация в 70-ом столбце!");
        }
        /*
         * 11
         * 81*/
        try {
            infoArray[10] = Math.round(sheet.getRow(pRowNumber).getCell(110)
                    .getNumericCellValue() * 100.00) / 100.00;
        } catch (Exception e) {
            infoArray[10] = -1;//Ошибка получения информации
            //FileException.callAlert("Некорректная информация в 80-ом столбце!");
        }
        /*
         * 12
         * 75*/
        try {
            infoArray[11] = Math.round(sheet.getRow(pRowNumber).getCell(104)
                    .getNumericCellValue() * 100.00) / 100.00;
        } catch (Exception e) {
            infoArray[11] = -1;//Ошибка получения информации
            //FileException.callAlert("Некорректная информация в 75-ом столбце!");
        }
        /*
         * 13
         * 99,101,103
         * Если есть хотя бы одно значение отличное от нуля, +*/
        try {
            if (((int) sheet.getRow(pRowNumber).getCell(134).getNumericCellValue() != 0) || ((int) sheet.getRow(pRowNumber).getCell(136).getNumericCellValue() != 0) || ((int) sheet.getRow(pRowNumber).getCell(138).getNumericCellValue() != 0)) {
                infoArray[12] = 1;
            } else {
                infoArray[12] = 0;
            }
        } catch (Exception e) {
            infoArray[12] = -1;
            //FileException.callAlert("Некорректная информация в 99-ом, или, 101-ом, или 103-ем столбце!");
        }

        /*
         * 14
         * 108,110,112,113,
         * Если есть значение больше или равно 0.5 то +*/
        try {
            if ((sheet.getRow(pRowNumber).getCell(143).getNumericCellValue()
                    >= 0.5) || (sheet.getRow(pRowNumber).getCell(145)
                    .getNumericCellValue() >= 0.5) || (sheet.getRow(pRowNumber)
                    .getCell(147).getNumericCellValue() >= 0.5) || (sheet.getRow(pRowNumber).getCell(148).getNumericCellValue() >= 0.5)) {
                infoArray[13] = 1;
            } else {
                infoArray[13] = 0;
            }

        } catch (Exception e) {
            infoArray[9] = -1;
            //FileException.callAlert("Некорректная информация в 108-ом, или, 110-ом, или 112-ем, или 113-ом столбце!");
        }

        return infoArray;
    }

    //////////////////////////////////////////
    //Chair Info
    public String GetUnivName() {
        try {
            return sheet.getRow(5).getCell(1).getStringCellValue();
        } catch (Exception e) {
            System.err.println("GetUnivName error!!!");
            return null;
        }
    }

    public String GetChName() {
        try {
            return sheet.getRow(5).getCell(2).getStringCellValue();
        } catch (Exception e) {
            System.err.println("GetChName error!!!");
            return null;
        }
    }

    public double GetChNpp() {
        try {
            return Math.round(sheet.getRow(30)
                    .getCell(151).getNumericCellValue() * 100.00) / 100.00;
        } catch (Exception e) {
            // FileException.callAlert("Некорректная информация в 115-ом столбце, 31-ой строке!");
            return -1;
        }
    }

    public double GetChStudyWork() {
        try {
            return Math.round(sheet.getRow(4).getCell(163)
                    .getNumericCellValue() * 100.00) / 100.00;
        } catch (Exception e) {
            //FileException.callAlert("Некорректная информация об учебной работе!");
            return -1;
        }
    }

    public double GetChMethodicalWork() {
        try {
            return Math.round(sheet.getRow(4).getCell(176)
                    .getNumericCellValue() * 100.00) / 100.00;
        } catch (Exception e) {
            //FileException.callAlert("Некорректная информация в столбце 16М!");
            return -1;
        }
    }

    public double GetChSinceWork() {
        try {
            return Math.round(sheet.getRow(4).getCell(240)
                    .getNumericCellValue() * 100.00) / 100.00;
        } catch (Exception e) {
            //FileException.callAlert("Некорректная информация о научной работе 65Н!");
            return -1;
        }
    }

    public double GetChMatBase() {
        try {
            return Math.round(sheet.getRow(4).getCell(250).getNumericCellValue() * 100.00) / 100.00;
        } catch (Exception e) {
            System.err.println("GetChMatBase error!!!");
            return -1;
        }
    }

    public double GetChIdWork() {
        try {
            return Math.round(sheet.getRow(5).getCell(251).getNumericCellValue() * 100.00) / 100.00;
        } catch (Exception e) {
            System.err.println("GetChIdWork error!!!");
            return -1;
        }
    }

    public double GetChSecurity() {
        try {
            return Math.round(sheet.getRow(5).getCell(252).getNumericCellValue() * 100.00) / 100.00;
        } catch (Exception e) {
            System.err.println("GetChSecurity error!!!");
            return -1;
        }
    }

    public double GetChRate() {
        try {
            return Math.round(sheet.getRow(4).getCell(253).getNumericCellValue() * 100.00) / 100.00;
        } catch (Exception e) {
            System.err.println("GetChRate error!!!");
            return -1;
        }
    }

    public double GetChQualRate() {
        try {
            return Math.round(sheet.getRow(30).getCell(152).getNumericCellValue() * 100.00) / 100.00;
        } catch (Exception e) {
            System.err.println("GetChQualRate error!!!");
            return -1;
        }
    }

    //Additional info for chair
    public double GetNPPS() {
        try {
            return sheet.getRow(4).getCell(157).getNumericCellValue();
        } catch (Exception e) {
            System.err.println("GetAdditionalInfo(the 2nd part) error: 1 parameter!");
            return -1;
        }
    }

    private double GetImaginaryColumnValue(int[] cellsArray, int row) {
        try {
            double sum = 0;
            int cellsArrayIndex = 0;
            int cellsArrayLength = cellsArray.length;
            while (sum == 0 && cellsArrayIndex < cellsArrayLength) {
                sum += sheet.getRow(row).getCell(cellsArray[cellsArrayIndex])
                        .getNumericCellValue();
                cellsArrayIndex++;
            }

            if (sum != 0) {
                return 1;
            } else {
                return 0;
            }
        } catch (Exception e) {
            System.err.println("GetImaginaryColumnValue error!!!");
            return -1;
        }
    }

    private double GetImaginaryColumnSum(int[] cellsArray, int personCount) {
        try {
            double sum = 0;
            for (int i = 5; i < personCount + 5; i++) {
                sum += GetImaginaryColumnValue(cellsArray, i);
            }
            return sum;
        } catch (Exception e) {
            System.err.println("GetImaginaryColumnSum error!!!");
            return -1;
        }
    }

    private double GetLastValueInColumn(int cell) {
        try {
            return sheet.getRow(30).getCell(cell).getNumericCellValue();
        } catch (Exception e) {
            System.err.println("GetLastValueInColumn error!!!");
            return -1;
        }
    }

    public double[] GetAdditionalInfoForChNpp() {
        double[] infoArray = new double[5];
        double NPPS = GetNPPS();
        infoArray[0] = Math.round((GetImaginaryColumnSum(new int[]{19, 20, 21, 22}, GetPersonCountFromFile()) * 100 / NPPS) * 100.00) / 100.00;
        infoArray[1] = Math.round((GetImaginaryColumnSum(new int[]{24, 25, 26}, GetPersonCountFromFile()) * 100 / NPPS) * 100.00) / 100.00;
        infoArray[2] = Math.round((GetLastValueInColumn(27) * 100 / NPPS) * 100.00) / 100.00;
        infoArray[3] = Math.round((GetLastValueInColumn(28) * 100 / NPPS) * 100.00) / 100.00;
        infoArray[4] = Math.round(sheet.getRow(30).getCell(151).getNumericCellValue() * 100.00) / 100.00;
        return infoArray;
    }

    public double[] GetAdditionalInfoForChStudyWork() {
        double[] infoArray = new double[3];
        infoArray[0] = Math.round(GetLastValueInColumn(54) * 100.00) / 100.00;
        infoArray[1] = Math.round(GetLastValueInColumn(55) * 100.00) / 100.00;
        infoArray[2] = Math.round(GetLastValueInColumn(51) * 100.00) / 100.00;
        return infoArray;
    }

    public double[] GetAdditionalInfoForChMethodicalWork() {
        double[] infoArray = new double[3];
        double NPPS = GetNPPS();
        infoArray[0] = Math.round((GetLastValueInColumn(59) * 100 / NPPS) * 100.00) / 100.00;
        infoArray[1] = Math.round(GetLastValueInColumn(61) * 100.00) / 100.00;
        infoArray[2] = Math.round(GetLastValueInColumn(62) * 100.00) / 100.00;
        return infoArray;
    }

    public double[] GetAdditionalInfoForChSinceWork() {
        double[] infoArray = new double[8];
        double NPPS = GetNPPS();
        infoArray[0] = Math.round(GetLastValueInColumn(78) * 100.00) / 100.00;
        infoArray[1] = Math.round(GetLastValueInColumn(81) * 100.00) / 100.00;
        infoArray[2] = Math.round((GetImaginaryColumnSum(new int[]{100}, GetPersonCountFromFile()) * 100 / NPPS) * 100.00) / 100.00;
        infoArray[3] = Math.round(GetLastValueInColumn(104) * 100.00) / 100.00;
        infoArray[4] = Math.round(GetLastValueInColumn(107) * 100.00) / 100.00;
        infoArray[5] = Math.round(GetLastValueInColumn(110) * 100.00) / 100.00;
        infoArray[6] = Math.round((GetImaginaryColumnSum(new int[]{134, 136, 138}, GetPersonCountFromFile()) * 100 / NPPS) * 100.00) / 100.00;
        infoArray[7] = Math.round((GetImaginaryColumnSum(new int[]{143, 145, 147, 148}, GetPersonCountFromFile()) * 100 / NPPS) * 100.00) / 100.00;

        return infoArray;
    }

    public double[] GetAdditionalInfoForChMatBase() {
        double[] infoArray = new double[4];
        infoArray[0] = Math.round(sheet.getRow(4).getCell(241).getNumericCellValue() * 100.00) / 100.00;
        infoArray[1] = Math.round(sheet.getRow(4).getCell(243).getNumericCellValue() / 2 * 100.00) / 100.00;
        infoArray[2] = Math.round(sheet.getRow(4).getCell(245).getNumericCellValue() * 100.00) / 100.00;
        infoArray[3] = Math.round(sheet.getRow(4).getCell(246).getNumericCellValue() * 2 * 100.00) / 100.00;
        return infoArray;
    }

    public int[] GetInfoForChair(Person p) {//вызов по объекту данного класса с соответствующим файлом, переданным в конструктор
        int[] infoArray = new int[14];
        return infoArray;
    }
}
