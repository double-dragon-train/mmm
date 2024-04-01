
export function formatDate(timestamp:number) {
  const date = new Date(timestamp);
  return `${date.toISOString().split('T')[0]} ${date.getHours()}:${date.getMinutes()}`;
}

