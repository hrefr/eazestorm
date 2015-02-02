package ru.eaze.locale;

import com.intellij.lang.ASTNode;
import com.intellij.lang.Language;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.TextRange;
import com.intellij.pom.PsiDeclaredTarget;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiManager;
import com.intellij.psi.PsiNamedElement;
import com.intellij.psi.PsiReference;
import com.intellij.psi.impl.PsiElementBase;
import com.intellij.psi.impl.source.resolve.reference.ReferenceProvidersRegistry;
import com.intellij.util.IncorrectOperationException;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class EazeLocaleDeclaration extends PsiElementBase implements PsiDeclaredTarget, PsiNamedElement {

    @NotNull
    private final PsiElement baseElement;
    @NotNull
    private final TextRange valueRange;

    EazeLocaleDeclaration(@NotNull PsiElement baseElement, @NotNull TextRange valueRange) {
        this.baseElement = baseElement;
        this.valueRange = valueRange;
    }

    @Override
    public PsiManager getManager() {
        return baseElement.getManager();
    }

    @NotNull
    @Override
    public Project getProject() {
        return baseElement.getProject();
    }


    /**
     * @return the range containing name. Range should be relative to {@link #getNavigationElement()} result
     */
    @Nullable
    @Override
    public TextRange getNameIdentifierRange() {
        return valueRange;
    }

    /**
     * Returns the language of the PSI element.
     *
     * @return the language instance.
     */
    @NotNull
    @Override
    public Language getLanguage() {
        return baseElement.getLanguage();
    }

    /**
     * Returns the array of children for the PSI element.
     * Important: In some implementations children are only composite elements, i.e. not a leaf elements
     *
     * @return the array of child elements.
     */
    @NotNull
    @Override
    public PsiElement[] getChildren() {
        return PsiElement.EMPTY_ARRAY;
    }

    /**
     * Returns the parent of the PSI element.
     *
     * @return the parent of the element, or null if the element has no parent.
     */
    @Override
    public PsiElement getParent() {
        return baseElement.getParent();
    }

    /**
     * Returns the text range in the document occupied by the PSI element.
     *
     * @return the text range.
     */
    @Override
    public TextRange getTextRange() {
        return baseElement.getTextRange();
    }

    /**
     * Returns the text offset of the PSI element relative to its parent.
     *
     * @return the relative offset.
     */
    @Override
    public int getStartOffsetInParent() {
        return baseElement.getStartOffsetInParent();
    }

    /**
     * Returns the length of text of the PSI element.
     *
     * @return the text length.
     */
    @Override
    public int getTextLength() {
        return baseElement.getTextLength();
    }

    /**
     * Finds a leaf PSI element at the specified offset from the start of the text range of this node.
     *
     * @param offset the relative offset for which the PSI element is requested.
     * @return the element at the offset, or null if none is found.
     */
    @Nullable
    @Override
    public PsiElement findElementAt(int offset) {
        return this;
    }

    /**
     * Returns the offset in the file to which the caret should be placed
     * when performing the navigation to the element. (For classes implementing
     * {@link com.intellij.psi.PsiNamedElement}, this should return the offset in the file of the
     * name identifier.)
     *
     * @return the offset of the PSI element.
     */
    @Override
    public int getTextOffset() {
        return baseElement.getTextOffset() + valueRange.getStartOffset();
    }

    /**
     * Returns the text of the PSI element.
     *
     * @return the element text.
     */
    @Override
    public String getText() {
        return baseElement.getText();
    }

    /**
     * Returns the text of the PSI element as a character array.
     *
     * @return the element text as a character array.
     */
    @NotNull
    @Override
    public char[] textToCharArray() {
        return baseElement.textToCharArray();
    }

    /**
     * Returns the AST node corresponding to the element.
     *
     * @return the AST node instance.
     */
    @Override
    public ASTNode getNode() {
        return baseElement.getNode();
    }

    @NotNull
    @Override
    public PsiReference[] getReferences() {
        return ReferenceProvidersRegistry.getReferencesFromProviders(this);
    }

    @NotNull
    public TextRange getValueRange() {
        return valueRange;
    }

    @NotNull
    public String getValue() {
        if (this.getText() != null) {
            return valueRange.substring(this.getText());
        }
        return "";
    }

    /**
     * Returns the name of the element.
     *
     * @return the element name.
     */
    @Override
    public String getName() {
        return this.getValue();
    }

    /**
     * Renames the element.
     *
     * @param name the new element name.
     * @return the element corresponding to this element after the rename (either <code>this</code>
     * or a different element if the rename caused the element to be replaced).
     * @throws com.intellij.util.IncorrectOperationException if the modification is not supported or not possible for some reason.
     */
    @Override
    public PsiElement setName(@NotNull String name) throws IncorrectOperationException {
        //TODO implementation
        throw new IncorrectOperationException();
    }
}
