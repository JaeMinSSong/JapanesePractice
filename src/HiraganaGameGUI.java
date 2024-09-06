import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class HiraganaGameGUI extends JFrame {
    // 히라가나와 발음 매핑
    private static final Map<String, String> hiraganaMap = new HashMap<>();

    static {
        // 기본 히라가나
        hiraganaMap.put("あ", "a");
        hiraganaMap.put("い", "i");
        hiraganaMap.put("う", "u");
        hiraganaMap.put("え", "e");
        hiraganaMap.put("お", "o");
        hiraganaMap.put("か", "ka");
        hiraganaMap.put("き", "ki");
        hiraganaMap.put("く", "ku");
        hiraganaMap.put("け", "ke");
        hiraganaMap.put("こ", "ko");
        hiraganaMap.put("さ", "sa");
        hiraganaMap.put("し", "shi");
        hiraganaMap.put("す", "su");
        hiraganaMap.put("せ", "se");
        hiraganaMap.put("そ", "so");
        hiraganaMap.put("た", "ta");
        hiraganaMap.put("ち", "chi");
        hiraganaMap.put("つ", "tsu");
        hiraganaMap.put("て", "te");
        hiraganaMap.put("と", "to");
        hiraganaMap.put("な", "na");
        hiraganaMap.put("に", "ni");
        hiraganaMap.put("ぬ", "nu");
        hiraganaMap.put("ね", "ne");
        hiraganaMap.put("の", "no");
        hiraganaMap.put("は", "ha");
        hiraganaMap.put("ひ", "hi");
        hiraganaMap.put("ふ", "fu");
        hiraganaMap.put("へ", "he");
        hiraganaMap.put("ほ", "ho");
        hiraganaMap.put("ま", "ma");
        hiraganaMap.put("み", "mi");
        hiraganaMap.put("む", "mu");
        hiraganaMap.put("め", "me");
        hiraganaMap.put("も", "mo");
        hiraganaMap.put("や", "ya");
        hiraganaMap.put("ゆ", "yu");
        hiraganaMap.put("よ", "yo");
        hiraganaMap.put("ら", "ra");
        hiraganaMap.put("り", "ri");
        hiraganaMap.put("る", "ru");
        hiraganaMap.put("れ", "re");
        hiraganaMap.put("ろ", "ro");
        hiraganaMap.put("わ", "wa");
        hiraganaMap.put("を", "wo");
        hiraganaMap.put("ん", "n");

        // 탁음
        hiraganaMap.put("が", "ga");
        hiraganaMap.put("ぎ", "gi");
        hiraganaMap.put("ぐ", "gu");
        hiraganaMap.put("げ", "ge");
        hiraganaMap.put("ご", "go");
        hiraganaMap.put("ざ", "za");
        hiraganaMap.put("じ", "ji");
        hiraganaMap.put("ず", "zu");
        hiraganaMap.put("ぜ", "ze");
        hiraganaMap.put("ぞ", "zo");
        hiraganaMap.put("だ", "da");
        hiraganaMap.put("ぢ", "ji");
        hiraganaMap.put("づ", "zu");
        hiraganaMap.put("で", "de");
        hiraganaMap.put("ど", "do");
        hiraganaMap.put("ば", "ba");
        hiraganaMap.put("び", "bi");
        hiraganaMap.put("ぶ", "bu");
        hiraganaMap.put("べ", "be");
        hiraganaMap.put("ぼ", "bo");

        // 반탁음
        hiraganaMap.put("ぱ", "pa");
        hiraganaMap.put("ぴ", "pi");
        hiraganaMap.put("ぷ", "pu");
        hiraganaMap.put("ぺ", "pe");
        hiraganaMap.put("ぽ", "po");

        // 요음
        hiraganaMap.put("きゃ", "kya");
        hiraganaMap.put("きゅ", "kyu");
        hiraganaMap.put("きょ", "kyo");
        hiraganaMap.put("しゃ", "sha");
        hiraganaMap.put("しゅ", "shu");
        hiraganaMap.put("しょ", "sho");
        hiraganaMap.put("ちゃ", "cha");
        hiraganaMap.put("ちゅ", "chu");
        hiraganaMap.put("ちょ", "cho");
        hiraganaMap.put("にゃ", "nya");
        hiraganaMap.put("にゅ", "nyu");
        hiraganaMap.put("にょ", "nyo");
        hiraganaMap.put("ひゃ", "hya");
        hiraganaMap.put("ひゅ", "hyu");
        hiraganaMap.put("ひょ", "hyo");
        hiraganaMap.put("みゃ", "mya");
        hiraganaMap.put("みゅ", "myu");
        hiraganaMap.put("みょ", "myo");
        hiraganaMap.put("りゃ", "rya");
        hiraganaMap.put("りゅ", "ryu");
        hiraganaMap.put("りょ", "ryo");
        hiraganaMap.put("ぎゃ", "gya");
        hiraganaMap.put("ぎゅ", "gyu");
        hiraganaMap.put("ぎょ", "gyo");
        hiraganaMap.put("じゃ", "ja");
        hiraganaMap.put("じゅ", "ju");
        hiraganaMap.put("じょ", "jo");
        hiraganaMap.put("びゃ", "bya");
        hiraganaMap.put("びゅ", "byu");
        hiraganaMap.put("びょ", "byo");
        hiraganaMap.put("ぴゃ", "pya");
        hiraganaMap.put("ぴゅ", "pyu");
        hiraganaMap.put("ぴょ", "pyo");

        // 촉음
        hiraganaMap.put("っ", "tsu");
    }

    private JLabel hiraganaLabel;
    private JRadioButton[] optionsButtons;
    private ButtonGroup buttonGroup;
    private JButton nextButton;
    private JButton restartButton;
    private String correctAnswer;

    public HiraganaGameGUI() {
        setTitle("히라가나 학습 게임");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // 히라가나 레이블 설정
        hiraganaLabel = new JLabel("히라가나", SwingConstants.CENTER);
        hiraganaLabel.setFont(new Font("SansSerif", Font.BOLD, 36));
        add(hiraganaLabel, BorderLayout.NORTH);

        // 보기 표시
        JPanel optionsPanel = new JPanel();
        optionsPanel.setLayout(new GridLayout(4, 1, 5, 5)); // 간격 추가
        optionsButtons = new JRadioButton[4];
        buttonGroup = new ButtonGroup();

        for (int i = 0; i < 4; i++) {
            optionsButtons[i] = new JRadioButton();
            optionsButtons[i].setFont(new Font("SansSerif", Font.PLAIN, 24));
            optionsButtons[i].setPreferredSize(new Dimension(200, 50));
            buttonGroup.add(optionsButtons[i]);
            optionsPanel.add(optionsButtons[i]);
        }

        add(optionsPanel, BorderLayout.CENTER);

        // 컨트롤 패널 설정
        JPanel controlPanel = new JPanel();
        nextButton = new JButton("다음");
        restartButton = new JButton("다시 시작");

        nextButton.setFont(new Font("SansSerif", Font.PLAIN, 18));
        restartButton.setFont(new Font("SansSerif", Font.PLAIN, 18));

        controlPanel.add(nextButton);
        controlPanel.add(restartButton);
        add(controlPanel, BorderLayout.SOUTH);

        // 버튼 동작 설정
        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkAnswer();
                loadNewQuestion();
            }
        });

        restartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadNewQuestion();
            }
        });

        loadNewQuestion();
    }

    private void loadNewQuestion() {
        ArrayList<String> hiraganaList = new ArrayList<>(hiraganaMap.keySet());
        Collections.shuffle(hiraganaList);
        String hiragana = hiraganaList.get(0);
        correctAnswer = hiraganaMap.get(hiragana);

        hiraganaLabel.setText("히라가나: " + hiragana);

        ArrayList<String> options = new ArrayList<>();
        options.add(correctAnswer);
        while (options.size() < 4) {
            String option = new ArrayList<>(hiraganaMap.values()).get(new Random().nextInt(hiraganaMap.size()));
            if (!options.contains(option)) {
                options.add(option);
            }
        }
        Collections.shuffle(options);

        for (int i = 0; i < 4; i++) {
            optionsButtons[i].setText(options.get(i));
            optionsButtons[i].setActionCommand(options.get(i));
        }
    }

    private void checkAnswer() {
        for (JRadioButton button : optionsButtons) {
            if (button.isSelected()) {
                if (button.getActionCommand().equals(correctAnswer)) {
                    JOptionPane.showMessageDialog(this, "정답입니다!");
                } else {
                    JOptionPane.showMessageDialog(this, "오답입니다. 정답은 " + correctAnswer + "입니다.");
                }
                return;
            }
        }
        JOptionPane.showMessageDialog(this, "선택된 보기가 없습니다.");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new HiraganaGameGUI().setVisible(true);
            }
        });
    }
}
