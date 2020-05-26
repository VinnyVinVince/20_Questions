// Node for QuestionTree.
public class QuestionNode {

    private String text;

    // Left.
    private QuestionNode yes;

    // Right.
    private QuestionNode no;

    // Branch 'question' constructor.
    public QuestionNode(String text, QuestionNode yes, QuestionNode no) {

        this.text = text;
        this.yes = yes;
        this.no = no;

    }

    // Leaf 'answer" constructor.
    public QuestionNode(String text) {

        this(text, null, null);

    }

    // Returns true if node is a leaf, false otherwise
    public boolean isAnswer() {

        return yes == null && no == null;

    }

    public void setYes(QuestionNode yes) {

        this.yes = yes;

    }

    public void setNo(QuestionNode no) {

        this.no = no;

    }

    public String text() {

        return text;

    }

    public QuestionNode yes() {

        return yes;

    }

    public QuestionNode no() {

        return no;

    }

}