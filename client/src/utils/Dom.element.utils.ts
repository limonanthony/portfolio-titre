import ElementId from '@/enums/Element.id.enum';

export default class DomElementUtils {
  public static scrollToId(id: ElementId) {
    const element = document.getElementById(id);
    if (element) element.scrollIntoView({ behavior: 'smooth' });
    else console.warn(`Element with id ${id} not found`);
  }
}
