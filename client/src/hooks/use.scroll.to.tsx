import ElementId from '@/enums/Element.id.enum';
import DomElementUtils from '@/utils/Dom.element.utils';

export default function useScrollTo() {
  const scrollToId = (id: ElementId) => () => DomElementUtils.scrollToId(id);
  return { scrollToId };
}
